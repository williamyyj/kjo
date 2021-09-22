package org.cc.db;



import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.cc.json.JSONObject;

import lombok.extern.log4j.Log4j2;



/**
 *
 * @author William
 */

@Log4j2
public class DSTomcatPool implements ICCDataSource<javax.sql.DataSource> {

    private JSONObject cfg;
    private DataSource ds;

    @Override
    public void init(JSONObject cfg) {
        this.cfg = cfg;
    }

    @Override
    public String id() {
        return (String) cfg.getOrDefault("id", "db");
    }

    @Override
    public DataSource getDataSource() {
        if (ds == null) {
            try {
                PoolProperties p = new PoolProperties();
                p.setUrl(cfg.optString("url"));
                p.setDriverClassName(cfg.optString( "driver"));
                p.setUsername(cfg.optString( "user"));
                p.setPassword(cfg.optString( "password"));
                p.setJmxEnabled(true);
                p.setTestWhileIdle(false);
                p.setTestOnBorrow(true);
                p.setValidationQuery("SELECT 1");
                p.setTestOnReturn(true);
                p.setValidationInterval(30000);
                p.setTimeBetweenEvictionRunsMillis(30000);
                p.setMaxActive(100);
                p.setInitialSize(3);
                p.setMaxWait(10000);
                p.setRemoveAbandonedTimeout(60);
                p.setMinEvictableIdleTimeMillis(30000);
                p.setMinIdle(10);
                p.setLogAbandoned(true);
                p.setRemoveAbandoned(true);
               // p.setAbandonWhenPercentageFull(100);

                p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
                  + "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
                ds = new DataSource() ;
                ds.setPoolProperties(p);
                log.info(p);
            } catch (Exception e) {
                log.error(e);
            }
        }
        return ds;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    @Override
    public void close() throws Exception {
        if (ds != null) {
            ds.close(true);
        }
        ds = null;
    }

    @Override
    public String info() {
        return ds.getPoolProperties().toString() ;
    }

}
