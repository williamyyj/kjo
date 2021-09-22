package org.cc.model;

import com.google.common.cache.CacheLoader;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.cc.util.CCPackage;

@Slf4j
public class CCFunCacheLoader extends CacheLoader<String, Object> {

    private static Map<String, Class<?>> _cache;

    private final static String pkg = "org.cc.fun";

    public static Map<String, Class<?>> cache() {
        if (_cache == null) {
            _cache = new HashMap<String, Class<?>>();
            CCPackage.scanPackage(_cache, pkg);
        }
        return _cache;
    }

    @Override
    public Object load(String funId) throws Exception {
        try {
            Class<?> cls = cache().get(funId);
            if (cls != null) {
                log.info("Load function [" + funId + "]" + cls);
                return cls.getDeclaredConstructor().newInstance();
            } else {
                String classId = pkg + "." + funId;
                cls = Class.forName(classId);
                return cls.getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {
            log.error("Can't find funId : " + funId);
            return null;
        }
    }

}