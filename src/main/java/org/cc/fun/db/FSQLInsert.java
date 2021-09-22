package org.cc.fun.db;


import java.util.List;
import java.util.function.Function;
import org.cc.model.CCField;


public class FSQLInsert implements Function<List<CCField>,String> {

    @Override
    public String apply(List<CCField> flds ) {

        CCField tb = flds.get(0);
        if (!"table".equals(tb.dt())) {
            throw new RuntimeException("CCField must tb field : " + tb);
        }
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into ").append(tb.name());
        proc_cols_name(sql, flds);
        sql.append("\r\n values ");
        proc_cols_value(sql, flds);
        proc_scope(sql,flds.get(1));
        return sql.toString();
    }

    private void proc_cols_name(StringBuilder sql, List<CCField> fields) {
        sql.append(" (");
        for (CCField field : fields) {
            if (!"table".equals(field.dt())  && !"auto".equals(field.cfg().optString("ft")) ) {
                sql.append(' ').append(field.name()).append(",");
            }
        }
        sql.setCharAt(sql.length() - 1, ')');
    }

    private void proc_cols_value(StringBuilder sql, List<CCField> fields) {
        sql.append(" (");
        for (CCField field : fields) {
            if (!"table".equals(field.dt()) && !"auto".equals(field.cfg().optString("ft")) ) {
                sql.append(" ${");
                sql.append(field.name()).append(',');
                sql.append(field.dt()).append(',');
                sql.append(field.id());
                sql.append("},");
            }
        }
        sql.setCharAt(sql.length() - 1, ')');
    }

    private void proc_scope(StringBuilder sql, CCField fld) {
        String jdbc = fld.cfg().optString("jdbc");
        if(jdbc.contains("identity")){
            sql.append(";SELECT SCOPE_IDENTITY();");
        }
    }    
}
