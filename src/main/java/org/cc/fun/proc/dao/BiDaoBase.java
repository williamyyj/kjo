package org.cc.fun.proc.dao;

import java.sql.PreparedStatement;
import org.cc.db.DBCmd;
import org.cc.db.ICCDB;
import org.cc.fun.db.BCCmdFill;
import org.cc.model.CCProcObject;

public class BiDaoBase {

    protected BCCmdFill cmdFill = new BCCmdFill();

    public boolean executeUpdate(CCProcObject proc, DBCmd cmd) {
        // 標準DML使用
        ICCDB db = proc.db();
        try (PreparedStatement ps = db.connection().prepareStatement(cmd.sqlString())) {

        } catch (Exception e) {

        }
        return false;
    }



    
    public boolean executeQuery(CCProcObject proc, DBCmd cmd ){
        ICCDB db = proc.db();
        try(PreparedStatement ps = db.connection().prepareStatement(cmd.sqlString())){
   
        } catch (Exception e) {
            
        }
        return false;
    }

 


}
