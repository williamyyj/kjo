package org.cc.model;

import org.cc.json.JSONArray;
import org.cc.json.JSONObject;

public class CCCmdModuleJAString extends CCCMParams {
    
    private JSONArray ja ;

    public  CCCmdModuleJAString(String line){
        ja = new JSONArray(line);
    }

    @Override
    public JSONObject jo() {
        return null;
    }

    @Override
    public JSONArray ja() {
        return ja;
    }

    @Override
    public String mid() {
        return ja.optString(0);
    }

    @Override
    public String aid() {
        return ja.optString(1,"");
    }

    
}
