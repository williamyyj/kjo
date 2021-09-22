package org.cc.model;

import java.util.List;
import java.util.Map;
import org.cc.json.JSONObject;



public interface ICCModule {
    
    Map<String,CCField>  fldMap();

    CCField field(String line);

    CCProcObject proc();

    JSONObject cfg();

    void init_moduule();

    List<CCField> dbFields(String metaId);

    String metaPath();

    String path();
    
}
