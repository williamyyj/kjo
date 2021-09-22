package org.cc.model;

import java.io.StringWriter;
import java.util.Map;
import org.cc.ICCField;
import org.cc.ICCType;
import org.cc.json.JSONException;
import org.cc.json.JSONObject;


public class CCField extends JSONObject implements ICCField {

    private static final long serialVersionUID = -403809901759836838L;

    protected ICCType<?> type;

    public CCField(){

    }
    
    public CCField(Map<?, ?> m) {
        super(m);
    }

    public CCField(String line){
        super(line);
    }

    public void __init__(JSONObject cfg)  {
        if(cfg.containsKey("type")){
            this.type = (ICCType<?>) cfg.opt("type");
            cfg.remove("type");
        }
        putAll(cfg);
    }

    /**
     * 資料庫欄位名
     *
     * @return
     */
    @Override
    public String name() {
        return optString("name", optString("id"));
    }

    /**
     * 系統識別值 （唯一)
     *
     * @return
     */
    @Override
    public String id() {
        return optString("id");
    }

    @Override
    public String dt() {
        return optString("dt");
    }

    @Override
    public String label() {
        return optString("label");
    }

    @Override
    public int dtSize() {
        return optInt("size", 0);
    }

    public String label(String lang) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * DB : P -> pk , F-> FK , I -> index , N -> not null
     *
     * @return
     */
    @Override
    public String ct() {
        return optString("ct", null);
    }

    /**
     *
     *
     * @return
     */
    @Override
    public String ft() {
        return optString("ft", null);
    }

    @Override
    public ICCType<?> type() {
        return type;
    }

    public JSONObject idxMap(){
        return optJSONObject("im");
    }

    @Override
    public String alias() {
        return optString("alias");
    }

    @Override
    public String fmt() {
        return optString("fmt");
    }

    @Override
    public String notes() {
        return optString("notes");
    }

    @Override
    public void setFieldValue(JSONObject row, Object value) {
        if (row != null) {
            row.put(name(), type().value(value));
        }
    }

    @Override
    public JSONObject cfg() {
        return this;
    }



    @Override
    public Object getFieldValue(JSONObject row) {
        Object ret = null;
        if (row != null) {
            ret = row.get( optString("alias"));
            if (ret == null) {
                ret = row.get(optString("id"));
            }
            if (ret == null) {
                ret = row.get(optString("name"));
            }
            return ret;
        } else {
            return ret;
        }
    }



  

    public String toString(int indentFactor) throws JSONException {
        StringWriter sw = new StringWriter();
        synchronized (sw.getBuffer()) {
            sw.write("{");
            toStringItem(sw,"id");
            toStringItem(sw,"dt");
            toStringItem(sw,"label");
            toValueItems(sw);
            StringBuffer sb =sw.getBuffer();
            sb.setLength(sb.length()-1);
            sb.append("}");
            return sw.toString();
        }
    }

    private void toStringItem(StringWriter sw, String key) {
        sw.write(quote(key)+":"+quote(optString(key))+",");
    }

    private void toValueItems(StringWriter sw) throws JSONException{
        for (final Entry<String, ?> entry : entrySet()) {
            String key = entry.getKey();
            Object v = entry.getValue();
            if("id".equals(key) || "dt".equals(key) || "label".equals(key)){
                continue;
            }
            sw.write(quote(key));
            sw.write(":");
            try{

                JSONObject.writeValue(sw, v, 0, 0);
            } catch(Exception e){

            }
            sw.write(",");
        }  
    }

}
