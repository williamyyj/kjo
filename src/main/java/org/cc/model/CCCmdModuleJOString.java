package org.cc.model;

import org.cc.json.JSONArray;
import org.cc.json.JSONObject;

public class CCCmdModuleJOString extends CCCMParams {
    
    private JSONObject jo ;
    
    public CCCmdModuleJOString(String line){
        jo = new JSONObject(line);
    }

    @Override
    public JSONObject jo() {
        return jo;
    }

    @Override
    public JSONArray ja() {
        return null;
    }

    @Override
    public String mid() {
        return jo.optString("mid");
    }

    @Override
    public String aid() {
        return jo.optString("aid");
    }
}
