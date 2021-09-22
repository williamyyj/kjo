package org.cc.db;


import java.util.HashMap;
import java.util.Map;
import org.cc.ICCType;
import org.cc.db.cmd.DBCmdAll;
import org.cc.db.cmd.DBCmdConst;
import org.cc.db.cmd.DBCmdEval;
import org.cc.db.cmd.DBCmdLike;
import org.cc.db.cmd.DBCmdOp;
import org.cc.db.cmd.DBCmdOp2;
import org.cc.db.cmd.DBCmdRM;
import org.cc.db.cmd.DBCmdSet;
import org.cc.json.JSONArray;
import org.cc.json.JSONObject;
import org.cc.model.CCProcObject;
import lombok.Data;

@Data
public class DBCmdField {
    

    private String id;
    private String dt;
    private String alias ;
    private ICCType<?> type ;
    private Object value;
    private DBCmdOp op;

    private static Map<String,DBCmdOp> opMap;
    static {
        if (opMap == null) {
            opMap = new HashMap<String, DBCmdOp>();
            opMap.put("=", new DBCmdOp2("=","=",1));
            opMap.put(">", new DBCmdOp2(">",">",1));
            opMap.put(">=",new DBCmdOp2(">",">=",1));
            opMap.put("<", new DBCmdOp2("<","<",1));
            opMap.put("<=",new DBCmdOp2("<=","<=",1));
            opMap.put("!=",new DBCmdOp2("!=","!=",1));
            opMap.put("<>",new DBCmdOp2("<>","<>",1));
            opMap.put("$like", new DBCmdLike("$like","like",2)); //   xxx%
            opMap.put("$all", new DBCmdAll("$all","like",3));   //   %xxxx%
            opMap.put("$set", new DBCmdSet("$set","=",4)); // for update 
            opMap.put("$rm", new DBCmdRM("$rm","",5));  // 移除最後 ","   
            opMap.put("@", new DBCmdConst("@","",6));  //    for table query or const 
            opMap.put("$expr", new DBCmdEval("$expr","",7));
            opMap.put("$eval", new DBCmdEval("$eval","",7));
        }
    }


    public DBCmdField(CCProcObject proc, String line){
        __parser_line(proc,line);
    }

    private void __parser_line(CCProcObject proc, String line) {
        String[] args = null;
        if (line.charAt(0) == '[') {
            JSONArray arr = new JSONArray(line);
            args = arr.toList().toArray(new String[0]);
        } else {
            args = line.split(",");
        }
        
        if(opMap.containsKey(args[0])){
            this.op = opMap.get(args[0]);
            this.id = args.length>1 ? args[1]: "";
            this.dt = args.length>2 ? args[2]: null;
            this.alias = args.length>3 ? args[3]: null;
        } else {
            this.id = args.length>0 ? args[0]: "";
            this.dt = args.length>1 ? args[1]: null;
            this.alias = args.length>2 ? args[2]: null;
        }

        if(dt!=null){
            this.type = proc.db().types().type(dt);
            JSONObject p = proc.params();
            Object value = null;
            if (p.containsKey(id)) {
                value = type.value(p.get(id));
            }
            if (value == null && alias != null && p.containsKey(alias)) {
                //Object av = row.get(alias);
                //System.out.println("===== debug av " + av);
                value = type.value(p.get(alias));
            }
            this.value = value;
        }
    }


    
}
