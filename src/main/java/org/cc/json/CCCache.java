package org.cc.json;

import java.io.File;
import java.util.concurrent.TimeUnit;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.cc.data.CCData;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CCCache {

    protected static LoadingCache<String, CCJSONFileItem> _cache;

    public static LoadingCache<String, CCJSONFileItem> cache() {
        if (_cache == null) {
            _cache = CacheBuilder.newBuilder().maximumSize(1000) // 記憶體中最多保留 1000 筆資料
                    .expireAfterAccess(30, TimeUnit.MINUTES)
                    .build(new CacheLoader<String, CCJSONFileItem>() {
                        @Override
                        public CCJSONFileItem load(String k) throws Exception {
                            throw new RuntimeException("Using get(key, new  Callable<>{} ... ");
                        }
                    });
        }
        return _cache;
    }

    public static JSONObject load(File f) {
        if (f.exists()) {
            try {
                String fname = f.getAbsolutePath();
                CCJSONFileItem item = cache().get(fname, new CCJSONFileItem(fname));
                return item.load();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public static JSONObject loadPropXML(File f) {
        if (f.exists()) {
            try {
                String fname = f.getAbsolutePath();
                CCJSONFileItem item = cache().get(fname, new CCJSONFileItem(fname));
                return item.load();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }



    public static JSONObject load(String base, String id) {
        File f = new File(base, id + ".json");
        if (f.exists()) {
            try {
                JSONObject ret = load(f);
                ret.put("$parent", f.getParent());
                return ret;
            } catch (Exception ex) {
                log.error("Can't load " + f + " \r\n" + ex.getMessage());
            }
        }
        log.debug("Can't find :"+f.getAbsolutePath());
        return null;
    }

    public static JSONObject loadXML(String base, String id) {
        File f = new File(base, id + ".xml");
        if (f.exists()) {
            try {
                String xml = CCData.loadString(f, "UTF-8");
                return XML.toJSONObject(xml);
            } catch (Exception ex) {
                log.error("Can't load " + f + " \r\n" + ex.getMessage());

            }
        }
        log.debug("Can't find :"+f.getAbsolutePath());
        return null;
    }



}
