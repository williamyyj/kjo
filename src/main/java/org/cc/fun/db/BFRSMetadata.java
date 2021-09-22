package org.cc.fun.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import org.cc.ICCType;
import org.cc.model.CCField;
import org.cc.type.CCTypes;

import lombok.extern.log4j.Log4j2;

/**
 *
 * @author william
 */

 @Log4j2
public class BFRSMetadata implements BiFunction<CCTypes, ResultSet, List<CCField>> {

    @Override
    public List<CCField> apply(CCTypes types, ResultSet rs) {
        List<CCField> mdFields = new  ArrayList<CCField>();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int len = rsmd.getColumnCount();
            for (int i = 0; i < len; i++) {
                int idx = i + 1;
                CCField fld = new CCField();
                String name = rsmd.getColumnName(idx);
                fld.put("id", name.toLowerCase());
                fld.put("name",name);
                fld.put("jdbc",rsmd.getColumnTypeName(idx));
                int dt = rsmd.getColumnType(idx);
                ICCType<?> type = types.type(dt);
                fld.put("type",type);
                fld.put("dt", type.dt());
                mdFields.add(fld);
            }
        } catch (SQLException ex) {
            log.error(ex);
        }
        return mdFields;
    }
}
