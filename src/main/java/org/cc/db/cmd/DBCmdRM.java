package org.cc.db.cmd;

import java.util.List;
import org.cc.db.DBCmdField;

/**
 *  Update ${table} set abc = ? ,  def = ? , ...    last=? , 
 */
public class DBCmdRM extends DBCmdOp {

    public DBCmdRM(String op, String cmd, int group) {
        super(op, cmd, group);
    }

    @Override
    public void toCommand(List<DBCmdField> qrFields, StringBuffer sql, DBCmdField field) {
        sql.setLength(sql.length()-1); // skip  ,         
    }
    
}
