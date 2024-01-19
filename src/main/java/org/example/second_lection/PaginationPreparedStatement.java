package org.example.second_lection;

import java.sql.*;

import static org.example.second_lection.BlobProcessing.*;

public class PaginationPreparedStatement {
    public static void main(String[] args) {
        String sql = "SELECT * FROM world.big_table ORDER BY name LIMIT ? OFFSET ?";
        int pageSize = 10;
        int pageNumber = 1; // Предположим нам нужна страница 3
        int offset = (pageNumber - 1) * pageSize;

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, pageSize);
            preparedStatement.setInt(2, offset);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next())
                    System.out.println(resultSet.getInt(1) + ": " + resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
