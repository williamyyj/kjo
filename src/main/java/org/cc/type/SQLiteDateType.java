package org.cc.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.log4j.Log4j2;


/**
 * @author william sqlite 沒有 date datetim time type
 */
@Log4j2
public class SQLiteDateType extends CCDateType {

    @Override
    public Date getRS(ResultSet rs, String name) throws SQLException {
        String text = rs.getString(name);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dslfmt);
           return sdf.parse(text);
        } catch (ParseException ex) {
            log.debug("Can't cast date : " + text);
        }
        return null;
    }

    @Override
    public void setPS(PreparedStatement ps, int idx, Object value) throws SQLException {
        Date d = value(value);
        if (value == null) {
            ps.setNull(idx, Types.VARCHAR);
        } else {
            String text = new SimpleDateFormat(dslfmt).format(d);
            ps.setObject(idx, text);
        }
    }

    @Override
    public String sql_value(Object value) {
        SimpleDateFormat sdf = new SimpleDateFormat(dslfmt);
        //SimpleDateFormat sdf2 = new SimpleDateFormat(afmt);
        if (value instanceof String) {
            String ret = ((String) value).replaceAll("'", "''");
            return "'" + ret + "'";
        } else if (value instanceof Date) {
            return "'" + sdf.format((Date) value) + "'";
        } else {
            return "NULL";
        }
    }

}
