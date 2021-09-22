package org.cc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import org.cc.ICCType;
import org.cc.fun.db.BCPSFill;
import org.cc.fun.db.BFRS2Row;
import org.cc.fun.db.BFRS2Rows;
import org.cc.fun.db.BFRSMetadata;
import org.cc.json.JSONArray;
import org.cc.json.JSONObject;
import org.cc.type.CCTypes;

import lombok.extern.log4j.Log4j2;


/**
 *
 * @author william
 */
@Log4j2
public class ProcBase {


 

    
    


    public void __release(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ex) {
                log.error(ex);
            }
        }
    }

    public void __release(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                log.error(ex);
            }
        }
    }

    public void rollback(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback();
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                log.error(ex);
            }
        }
    }

}
