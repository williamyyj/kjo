package org.cc.db.cmd;

import java.util.List;

import org.cc.db.DBCmdField;

public abstract class DBCmdOp {

    private String op;
    private String cmd;
    private int group;
    
    public DBCmdOp(String op, String cmd, int group){
        this.op = op;
        this.cmd = cmd ;
        this.group = group ;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String toString(){
        return "DBQueryOP("+op+","+cmd+","+group+")";
    }

    public abstract void toCommand(List<DBCmdField> qrFields, StringBuffer sql, DBCmdField field);

    
}
