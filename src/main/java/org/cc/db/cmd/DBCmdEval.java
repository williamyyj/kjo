package org.cc.db.cmd;

import java.util.List;
import org.cc.db.DBCmdField;

public class DBCmdEval extends DBCmdOp {

    public DBCmdEval(String op, String cmd, int group) {
        super(op, cmd, group);
    }

    @Override
    public void toCommand(List<DBCmdField> qrFields, StringBuffer sql, DBCmdField field) {
        // TODO Auto-generated method stub
        
    }
    
}
