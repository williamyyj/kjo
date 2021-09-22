package org.cc.fun.proc.dao;

import java.util.function.BiFunction;
import org.cc.IAProxyClass;
import org.cc.json.JSONObject;
import org.cc.model.CCCMParams;
import org.cc.model.CCProcObject;
import org.cc.model.ICCModule;

@IAProxyClass(id="row")
public class BiDBRow extends BiDaoBase implements BiFunction<CCProcObject,String,JSONObject>{

    @Override
    public JSONObject apply(CCProcObject proc, String line) {
        CCCMParams cmp = CCCMParams.newInstance(line);
        ICCModule md = proc.module(cmp.mid());
        
        return null;
    }
    
}
