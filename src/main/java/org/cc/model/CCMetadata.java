package org.cc.model;

import java.util.ArrayList;
import java.util.List;
import org.cc.json.CCCache;
import org.cc.json.CCPath;
import org.cc.json.JSONArray;
import org.cc.json.JSONObject;
import org.cc.type.CCTypes;

/**
 * prod : meta prj : $metadata
 */
public class CCMetadata {

    private ICCModule module;


    public CCMetadata(ICCModule module) {
        this.module = module;
        __init_metadata();
    }

    private void __init_metadata() {
        __proc_meta(module.cfg(),module.cfg().optString("id",null),"");
        __proc_metadata();
    }

    private JSONObject metaCfg(String metaId) {
        return CCCache.load(module.metaPath(), metaId);
    }

    private void __proc_metadata() {
        JSONArray ja = module.cfg().optJSONArray("$metadata");
        if(ja!=null){
            ja.forEach(o->{
                String[] items = ((String)o).split(",");
                String metaId = items.length>1 ? items[1] : items[0];
                String alias = items.length>1 ? items[0] : "";
                JSONObject cfg = metaCfg(metaId);
                __proc_meta(cfg, metaId, alias);
            });
        }

    }

    

    private void __proc_meta(JSONObject cfg,String metaId, String alias) {
        JSONArray flds = cfg.optJSONArray("meta"); 
        if (flds != null) {
            CCTypes types = module.proc().db().types();
            for (Object o : flds) {
                JSONObject fldCfg = (JSONObject) o;
                String dt = fldCfg.optString("dt");
                fldCfg.put("type", types.type(dt));
                CCField field = new CCField();
                field.__init__(fldCfg);
                if(alias.length()>0){
                    module.fldMap().put( alias+ "." + field.id(), field); // 唯一如果有
                }
                module.fldMap().put(metaId+"."+field.id(), field);
            }
            CCPath.set(module.cfg(), "$tbFields:"+metaId, cfg.optString("$tbFields"));
            if(alias.length()>0){
                CCPath.set(module.cfg(), "$tbFields:"+alias, cfg.optString("$tbFields"));
            }
        }
    }

  
}
