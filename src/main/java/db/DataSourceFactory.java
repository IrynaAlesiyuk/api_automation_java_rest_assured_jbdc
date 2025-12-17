package db;

import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;


public class DataSourceFactory {
    private static final JdbcDataSource DS = new JdbcDataSource();

    static {
        DS.setURL("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        DS.setUser("sa");
        DS.setPassword("");
    }

    public static DataSource getDataSource() {
        return DS;
    }
}
