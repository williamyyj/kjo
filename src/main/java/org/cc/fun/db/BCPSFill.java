package org.cc.fun.db;

import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.function.BiConsumer;

import org.cc.json.JSONArray;
import org.cc.json.JSONObject;

import lombok.extern.log4j.Log4j2;



/**
 * 直接使用SQL 的方式
 * @author william
 */
@Log4j2
public class BCPSFill implements BiConsumer<PreparedStatement, Object[]> {

    private volatile boolean pmdKnownBroken = false;

    @Override
    public void accept(PreparedStatement ps, Object[] params) {
        // nothing to do here
        ParameterMetaData pmd = null;
        try {           
            if (params == null) {
                return;
            }
            if (!pmdKnownBroken) {
                pmd = ps.getParameterMetaData();
                int stmtCount = pmd.getParameterCount();
                int paramsCount = params.length;
                if (stmtCount != paramsCount) {
                    log.error("Wrong number of parameters: expected " + stmtCount + ", was given " + paramsCount);
                    return;
                }
            }
            
            for (int i = 0; i < params.length; i++) {
                set_param(pmd, ps, i, params[i]);
            }
        } catch (Exception e) {
            log.error(e);
        }

    }

    private void set_param(ParameterMetaData pmd, PreparedStatement ps, int idx, Object value) throws SQLException {
        if (value != null) {
            if (value instanceof Date) {
                ps.setTimestamp(idx + 1, new Timestamp(((Date) value).getTime()));
            } else if (value instanceof JSONArray) {
                ps.setObject(idx + 1, ((JSONArray)value).toString());
            } else if (value instanceof JSONObject) {
                ps.setObject(idx + 1, ((JSONObject)value).toString());
            } else {
                ps.setObject(idx + 1, value);
            }
        } else {
            int sqlType = Types.VARCHAR;
            if (!pmdKnownBroken) {
                try {
                    sqlType = pmd.getParameterType(idx + 1);
                } catch (SQLException e) {
                    pmdKnownBroken = true;
                }
            }
            ps.setNull(idx+ 1, sqlType);
        }
    }
}
