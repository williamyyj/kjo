package org.cc.db.cmd;

import java.util.List;
import org.cc.db.DBCmdField;

public class DBCmdSet extends DBCmdOp {

    public DBCmdSet(String op, String cmd, int group) {
        super(op, cmd, group);
    }

    @Override
    public void toCommand(List<DBCmdField> qrFields, StringBuffer sql, DBCmdField field) {
        if(field.getValue()!=null){
            // update xxxx set  
            //    id = ?,
            sql.append("  ").append(field.getId()).append(" = ?,");
            qrFields.add(field);
        }   
    }
    
}
