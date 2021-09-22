package org.cc.fun.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import org.cc.json.JSONObject;
import org.cc.model.CCField;


/**
 *
 * @author william
 */
public class BFRS2Rows implements BiFunction<List<CCField>, ResultSet, List<JSONObject>> {

    private BiFunction<List<CCField>, ResultSet, JSONObject> rs2row = new BFRS2Row();

    @Override
    public List<JSONObject> apply(List<CCField> mdFields, ResultSet rs) {
        List<JSONObject> data = new ArrayList<JSONObject>();
        try {
            while (rs.next()) {
                data.add(rs2row.apply(mdFields, rs));
            }
        } catch (Exception e) {
        }
        return data;
    }

}
