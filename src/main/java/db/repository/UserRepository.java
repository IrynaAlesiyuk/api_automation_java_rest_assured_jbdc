package db.repository;


import db.DbClient;
import db.model.UserDb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserRepository {

    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    private static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT PRIMARY KEY, email VARCHAR(255))";

    private static final String INSERT =
            "INSERT INTO users (id, email) VALUES (?, ?)";

    private static final String EXISTS =
            "SELECT 1 FROM users WHERE id = ?";
    private static final String SELECT_BY_ID = "SELECT id, email FROM users WHERE id = ?";


    private final DbClient db;

    public UserRepository(DbClient db) {
        this.db = db;
        logger.info("Creating users table if not exists");
        db.execute(CREATE_TABLE);
    }

    public void save(int id, String email) {
        logger.info("Saving user with id={} and email={}", id, email);
        db.execute(INSERT, id, email);
    }

    public boolean exists(int id) {
        boolean result = db.exists(EXISTS, id);
        logger.info("Checking existence of user with id={} : {}", id, result);
        return result;
    }

    public UserDb findById(int id) {
        return db.queryOne(
                SELECT_BY_ID,
                rs -> new UserDb(rs.getInt("id"), rs.getString("email")), // Lombok User
                id
        );
    }
}
