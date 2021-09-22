package org.cc;

import org.cc.json.JSONObject;

/**
 *  jdbc : alias
 *  jt : jdbcType int value
 * @author william
 */
public interface ICCField extends ICCInit<JSONObject> {

    public String ct(); // database  P , M 

    public String dt(); //  主型別

    public String ft(); // 

    public JSONObject idxMap();

    public String name();

    public String label();

    public String alias();

    public String id();

    public String fmt(); // 格式化使用

    public String notes();

    public int dtSize();

    public ICCType<?> type();

    public JSONObject cfg();

    public void setFieldValue(JSONObject row, Object value);

    public Object getFieldValue(JSONObject row);

}
