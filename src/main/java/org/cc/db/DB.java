package org.cc.db;

import org.cc.type.CCTypes;

/**
 *
 * @author william
 */
public class DB extends DBBase {

    public DB(String base) {
        super(base);
    }

    public DB(String base, String dbId) {
        super(base, dbId);
    }

    @Override
    protected void init_components() {
        super.init_components();
        super.types = new CCTypes(cfg.optString("database"));
    }

    @Override
    public CCTypes types() {
        return types;
    }

}
