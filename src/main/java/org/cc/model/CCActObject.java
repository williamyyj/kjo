package org.cc.model;

import org.cc.json.JSONObject;

public class CCActObject {
    
    private ICCModule cm;

    private String aid;

    private JSONObject cfg ;

    
    public CCActObject(ICCModule cm, String aid){
        this.cm = cm;   
        this.aid = aid ;
        __init_act();
    }

    private void __init_act() {
        this.cfg = cm.cfg().optJSONObject(aid);
        if(cfg==null){
            
        }
    }

    public JSONObject cfg(){
        return cfg;
    }
}
