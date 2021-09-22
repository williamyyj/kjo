package org.cc.fun.proc;

import java.util.List;
import java.util.function.BiFunction;
import org.cc.IAProxyClass;
import org.cc.db.DBRow;
import org.cc.model.CCProcObject;

/**
 * example : 
 *  CCProcUtil.exec(proc,"row@metaId,actId");
 */
@IAProxyClass(id="rows")
public class BiProcRows implements BiFunction<CCProcObject,String,List<DBRow>> {

    @Override
    public List<DBRow> apply(CCProcObject t, String u) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
