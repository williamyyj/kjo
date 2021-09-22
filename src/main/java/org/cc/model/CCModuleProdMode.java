package org.cc.model;

import org.cc.json.JSONObject;

public class CCModuleProdMode extends CCModule {

    public CCModuleProdMode(CCProcObject proc, String mid){
        super(proc,mid);
    }

    @Override
    public void init_moduule() {
        // TODO Auto-generated method stub
        
    }


    public String metaPath(){
        return this.proc.base()+proc.prefix();
    }

}
