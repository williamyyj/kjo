package org.cc.fun.proc;

import java.util.function.BiFunction;
import org.cc.IAProxyClass;
import org.cc.db.DBRow;
import org.cc.model.CCProcObject;

/**
 * example : 
 *  CCProcUtil.exec(proc,"row@metaId,actId");
 */
@IAProxyClass(id="row")
public class BiProcRow implements BiFunction<CCProcObject,String,DBRow> {

    @Override
    public DBRow apply(CCProcObject proc, String cmd) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
