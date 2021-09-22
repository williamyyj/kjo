package org.cc.model;

import org.cc.json.JSONObject;

/**
 * ${base}/module/${mId}.json ---> only metadata
 * ${base}/module/${mId}_${actId}.json
 * ${base}/module/$meta/${metaId}.json
 * 
 * 
 */
public class CCModulePrjMode extends CCModule {
    
     public CCModulePrjMode(CCProcObject proc, String mid){
         super(proc,mid);
     }

    @Override
    public void init_moduule() {
        init_metadata();
        
    }

    private void init_metadata() {
        new CCMetadata(this);
    }

    
    public String metaPath(){
        return this.proc.base()+proc.prefix()+"/$meta";
    }
    
}
