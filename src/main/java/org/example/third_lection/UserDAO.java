package org.example.third_lection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {

    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void addUser(User user) throws SQLException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "INSERT INTO users (id, name) VALUES (?, ?)"
                     )) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.executeUpdate();
        }
    }
}
