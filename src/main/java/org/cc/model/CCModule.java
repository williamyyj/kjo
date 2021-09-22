package org.cc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.cc.json.CCCache;
import org.cc.json.CCPath;
import org.cc.json.JSONArray;
import org.cc.json.JSONObject;

/**
 * 取代舊版metadata
 */
public abstract class CCModule implements ICCModule {

    protected CCProcObject proc;

    protected Map<String,CCField> fldMap = new HashMap<>();

    protected Map<String,CCActObject> actMap = new HashMap<>(); 
    
    protected JSONObject cfg;

    protected String mid ;

    
    
    public CCModule(CCProcObject proc, String mid){
        this.mid = mid;
        String mdPath = proc.base() +proc.prefix();
        this.cfg = CCCache.load(mdPath, mid);
        this.proc = proc;
        init_moduule();
    }

 
    public JSONObject cfg(){
        return cfg;
    }

    public CCProcObject proc(){
        return this.proc;
    }

    public Map<String, CCField> fldMap(){
        return fldMap;
    }

    public CCField field(String line){
        return null;
    }

    public List<CCField> dbFields(String metaId){
        List<CCField> flds = new ArrayList<>();
        JSONArray ja = CCPath.list(cfg(), "$tbFields:"+metaId);
        ja.forEach(o->{
            CCField fld = fldMap().get(metaId+"."+o);
            flds.add(fld);
        });
        return flds;
    }

    public CCActObject act(String aid){
        CCActObject ret = actMap.get(aid);
        if(ret==null){
            ret = new CCActObject(this,aid);
            actMap.put(aid,ret);
        }
        return ret;
    }

    public String path(){
        return proc.base+proc.prefix;
    }

}
