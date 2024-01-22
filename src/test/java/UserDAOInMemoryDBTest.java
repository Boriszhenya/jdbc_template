import org.example.third_lection.User;
import org.example.third_lection.UserDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDAOInMemoryDBTest {

    private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";

    private Connection connection;

    @BeforeEach
    public void setUp() throws SQLException {
        connection = DriverManager.getConnection(JDBC_URL);
        initializeDatabase();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    public void testAddUser() throws SQLException {
        // Arrange
        UserDAO userDAO = new UserDAO(connection);
        User user = new User(1, "John");

        // Act
        userDAO.addUser(user);

        // Assert
        // На самом деле нужно проверять что записались именно те данные, что были переданы
        assertEquals(1, getUserCount());
    }

    private void initializeDatabase() throws SQLException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(255))")
        ) {
            preparedStatement.executeUpdate();
        }
    }

    private int getUserCount() throws SQLException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT COUNT(*) FROM users");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            resultSet.next();
            return resultSet.getInt(1);
        }
    }
}

