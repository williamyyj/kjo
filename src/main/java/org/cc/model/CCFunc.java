package org.cc.model;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.BiFunction;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CCFunc {

    private static LoadingCache<String, Object> _cache;

    //private static Pattern pNum = Pattern.compile("^[0-9]*$");



    static LoadingCache<String, Object> cache() {
        if (_cache == null) {
            _cache = CacheBuilder
              .newBuilder()
              .maximumSize(1000) // 記憶體中最多保留 1000 筆資料
              .expireAfterWrite(120, TimeUnit.MINUTES) // 資料生命週期為寫入後 120 分鐘
              .build(new CCFunCacheLoader());
        }
        return _cache;
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T apply(String funId, Object params) {
        try{
            return (T) ((Function) cache().get(funId)).apply(params);
        } catch(Exception e){  	
        	e.printStackTrace();
            log.info("Can't apply function {} : {} " , funId , params);
            throw new RuntimeException(e); // for @Transational
        }
        
    }

    @SuppressWarnings("unchecked")
	public static <T> T apply(String funId, Object t , Object u) {
        try{
            return (T)((BiFunction) cache().get(funId)).apply(t,u);
        } catch(Exception e){
        	e.printStackTrace();
            log.info("Can't apply bifunction {} : {} , {} " , funId ,  t, u);
            throw new RuntimeException(e);  // for @Transational
        }
    }
    
  
 
}
