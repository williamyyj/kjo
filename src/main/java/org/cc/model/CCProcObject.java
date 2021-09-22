package org.cc.model;

import org.cc.db.DB;
import org.cc.db.ICCDB;
import org.cc.json.JSONObject;
import lombok.extern.log4j.Log4j2;
import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author william
 */

@Log4j2
public class CCProcObject extends JSONObject implements Closeable {

    private static final long serialVersionUID = -7752353211951757328L;
    public final static String pre_metadata = "$metadata";
    public final static String pre_module = "$module";
    public final static String pre_cfg = "$cfg";
    public final static String pre_ff = "$ff"; //  field function 
    public final static String pre_fp = "$fp"; //  表單回傳或初始資料
    public final static String pre_fields = "$fields"; //  表單回傳或初始資料
    public final static String act = "$act";
    public final static String cmd = "$cmd";
    public final static String prjPrefix = "/module"; //  專案開發
    public final static String prodPrefix = "/dp/metadata"; // 產品
    public final static int attr_self = 0;
    public final static int attr_params = 1;
    public final static int attr_request = 2;
    public final static int attr_session = 3;
    public final static int attr_app = 4;

    protected String base;
    protected String dbId;
    protected String prefix ;
    protected boolean isProdMode = true ;
    protected Map<String,CCModule> cms = new HashMap<>();


    public CCProcObject(String base) {
        this(base,"db",true);
    }

    public CCProcObject(String base, String dbId) {
        this(base,dbId,true);
    }

    public CCProcObject(String base, boolean isProdMode) {
        this(base,"db",isProdMode);
    }
    
    public CCProcObject(String base, String dbId, boolean isProdMode) {
        this.base = base;
        this.dbId = dbId;
        this.prefix = isProdMode ? prodPrefix : prjPrefix;
        this.isProdMode = isProdMode;
        put(pre_fp, new JSONObject());
    }


 

    

    @Override
    public void close() throws IOException {
        log.debug("release db ....");
        db().release();
    }

    public JSONObject fp() {
        return optJSONObject(pre_fp);
    }

    /*
     *   monk object 
     */
    public Object get(int fld, String name, Object dv) {
        switch (fld) {
            case attr_self:
                return (containsKey(name)) ? get(name) : dv;
            case attr_params:
                return fp().containsKey(name) ? fp().get(name) : dv;
            case attr_request:
                return containsKey("$req_" + name) ? get("$req_" + name) : dv;
            case attr_session:
                return containsKey("$sess_" + name) ? get("$sess_" + name) : dv;
            case attr_app:
                return containsKey("$app_" + name) ? get("$app_" + name) : dv;
        }
        return dv;
    }

    public Object set(int fld, String name, Object value) {
        switch (fld) {
            case attr_self:
                return put(name, value);
            case attr_params:
                return fp().put(name, value);
            case attr_request:
                return put("$req_" + name, value);
            case attr_session:
                return put("$sess_" + name, value);
            case attr_app:
                return put("$app_" + name, value);
        }
        return null;
    }

    public String base() {
        return this.base;
    }

    public String prefix(){
        return this.prefix;
    }

    public ICCDB db() {
        return (ICCDB) this.getOrDefault(dbId, init_db());
    }

    private Object init_db() {
        ICCDB db = (ICCDB) get(dbId);
        if (db == null) {
            db = new DB(base,dbId);
            put(dbId, db);
        }
        return db;
    }

    public JSONObject params() { //
        if (!this.containsKey("$")) {
            put("$", new JSONObject());
        }
        return optJSONObject("$");
    }

    public CCModule module(String mid){
        CCModule cm = cms.get(mid);
        if(cm==null){
           cm = isProdMode ? new CCModuleProdMode(this, mid) : new CCModulePrjMode(this, mid);
        }
        return cm;
    }

}
