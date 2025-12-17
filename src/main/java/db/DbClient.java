package db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbClient {

    private static final Logger logger = LoggerFactory.getLogger(DbClient.class);

    private final DataSource dataSource;

    public DbClient(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void execute(String sql, Object... params) {
        logger.info("Executing SQL: {} with params: {}", sql, params);
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            ps.executeUpdate();
            logger.info("SQL executed successfully");
        } catch (SQLException e) {
            logger.error("Error executing SQL: {}", sql, e);
            throw new RuntimeException(e);
        }
    }

    public boolean exists(String sql, Object... params) {
        logger.info("Checking existence with SQL: {} and params: {}", sql, params);
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            boolean result = ps.executeQuery().next();
            logger.info("Existence check result: {}", result);
            return result;
        } catch (SQLException e) {
            logger.error("Error executing exists SQL: {}", sql, e);
            throw new RuntimeException(e);
        }
    }

    public <T> T queryOne(String sql, RowMapper<T> mapper, Object... params) {
        logger.info("Executing query: {} with params: {}", sql, params);
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                T result = mapper.map(rs);
                logger.info("Query result: {}", result);
                return result;
            } else {
                logger.info("Query returned no results");
                return null;
            }
        } catch (SQLException e) {
            logger.error("Error executing query: {}", sql, e);
            throw new RuntimeException(e);
        }
    }

    @FunctionalInterface
    public interface RowMapper<T> {
        T map(ResultSet rs) throws SQLException;
    }
}
