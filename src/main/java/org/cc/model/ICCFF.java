package org.cc.model;


import org.cc.json.JSONObject;



/**
 * @author william
 * @param <R>
 */
public interface ICCFF<R> {

    public void init(CCProcObject proc) throws Exception ; 
    
    public R as(JSONObject row, String id);
    
    public JSONObject cfg();

}
