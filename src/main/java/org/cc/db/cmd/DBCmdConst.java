package org.cc.db.cmd;

import java.util.List;
import org.cc.db.DBCmdField;

public class DBCmdConst extends DBCmdOp {

    public DBCmdConst(String op, String cmd, int group) {
        super(op, cmd, group);
    }

    @Override
    public void toCommand(List<DBCmdField> qrFields, StringBuffer sql, DBCmdField field) {
        if(field.getValue()!=null){
            sql.append(field.getValue());
        }
    }
    
}
