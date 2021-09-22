package org.cc.fun.proc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.function.BiFunction;
import org.cc.IAProxyClass;
import org.cc.db.DBCmd;
import org.cc.db.ICCDB;
import org.cc.fun.db.FSQLInsert;
import org.cc.model.CCCMParams;
import org.cc.model.CCProcObject;
import org.cc.model.ICCModule;

@IAProxyClass(id="dao_add")
public class BiDaoRowAdd extends BiDaoBase implements BiFunction<CCProcObject,String,Long>{

    private FSQLInsert fsql = new FSQLInsert();
    
    @Override
    public Long apply(CCProcObject proc, String line) {
        CCCMParams cmp = CCCMParams.newInstance(line);
        ICCModule md = proc.module(cmp.mid());
        String sql = fsql.apply(md.dbFields(cmp.aid()));
        DBCmd cmd = new DBCmd(proc,sql);
        return insert(proc,cmd);
    }

    public long insert(CCProcObject proc, DBCmd cmd) {
        // 給Insert使用回傳PK用
        ICCDB db = proc.db();
        try (PreparedStatement ps = db.connection().prepareStatement(cmd.sqlString())) {
            cmdFill.accept(ps, cmd.qrFileds());
            ps.execute();
            if (ps.getMoreResults()) {
                try (ResultSet rs = ps.getResultSet();) {
                    if (rs != null && rs.next()) {
                        return rs.getLong(1);
                    }
                } 
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1L;
    }
    
}
