package org.cc.db.cmd;

import java.util.List;
import org.cc.db.DBCmdField;

public class DBCmdAll extends DBCmdOp {

    public DBCmdAll(String op, String cmd, int group) {
        super(op, cmd, group);
    }

    @Override
    public void toCommand(List<DBCmdField> qrFields, StringBuffer sql, DBCmdField field) {
        if(field.getValue()!=null){
            field.setValue("%"+field.getValue()+"%");
            sql.append(" and ").append(field.getId()).append(" ").append(field.getOp().getCmd()).append(" ?");
            qrFields.add(field);
        } 
    }
        
}
