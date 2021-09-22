package org.cc.fun.db;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.BiFunction;
import org.cc.json.JSONObject;
import org.cc.model.CCField;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author william
 */
@Log4j2
public class BFRS2Row implements BiFunction<List<CCField>, ResultSet, JSONObject> {

    @Override
    public JSONObject apply(List<CCField> mdFields, ResultSet rs) {
        JSONObject row = new JSONObject();
        for(CCField fld : mdFields){
            try {
                Object v = fld.type().getRS(rs, fld.name());
                fld.setFieldValue(row, v);
            } catch (SQLException ex) {
                log.error("fail set value : "+ fld.cfg(), ex);
            }
        }
        return row;
    }

}
