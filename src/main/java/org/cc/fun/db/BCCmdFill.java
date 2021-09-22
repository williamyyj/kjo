package org.cc.fun.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.function.BiConsumer;
import org.cc.db.DBCmdField;
import lombok.extern.log4j.Log4j2;

/**
 * 
 */
@Log4j2
public class BCCmdFill implements BiConsumer<PreparedStatement, List<DBCmdField> > {

    @Override
    public void accept(PreparedStatement ps, List<DBCmdField> fields) {
        int idx = 1;
        for(DBCmdField fld : fields){
            try {
                fld.getType().setPS(ps, idx, fld.getValue());
            } catch (SQLException e) {
                log.error("PreparedStatement set fial : "+fld,e);
            }
            idx++;
        }
    }
    
}
