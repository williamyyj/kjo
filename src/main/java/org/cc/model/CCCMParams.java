package org.cc.model;

import org.cc.json.JSONArray;
import org.cc.json.JSONObject;

/**
 *   mid,aid,....
 *   {}
 */
public abstract class CCCMParams {

    public static CCCMParams newInstance(String line){    
        if(line !=null){
            line = line.trim();
            char c = line.length()>0 ? line.charAt(0) : 0;
            switch(c){
                case '[' : return new CCCmdModuleJAString(line);
                case '{' : return new CCCmdModuleJOString(line);
                default :
                    return new CCCmdModuleJAString("["+line+"]");
            }

        } 
        throw new RuntimeException("CCCmdModuleString can't parase "+line);
    }

    public abstract String mid();

    public abstract String aid();
 
    public abstract JSONObject jo();
    
    public abstract JSONArray ja();

}
