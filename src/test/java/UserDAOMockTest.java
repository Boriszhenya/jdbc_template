import org.example.third_lection.User;
import org.example.third_lection.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class UserDAOMockTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    private UserDAO userDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        userDAO = new UserDAO(connection);

        // Mocking поведения Connection и prepareStatement
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    @Test
    public void testAddUser() throws SQLException {
        // Arrange
        User user = new User(1, "John");

        // Act
        userDAO.addUser(user);

        // Assert
        verify(connection, times(1)).prepareStatement(anyString());
        verify(preparedStatement, times(1)).setInt(1, user.getId());
        verify(preparedStatement, times(1)).setString(2, user.getName());
        verify(preparedStatement, times(1)).executeUpdate();
    }
}

