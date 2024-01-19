package org.example.second_lection;

import java.sql.*;

import static org.example.second_lection.BlobProcessing.*;

public class BatchProcessing {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "INSERT INTO world.employees (employee_id, employee_name, salary) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Снова получаем значение ID
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT MAX(employee_id) FROM world.employees");
                int maxIndex = 0;
                while (resultSet.next())
                    maxIndex = resultSet.getInt(1);
                // Добавляем запросы в batch
                preparedStatement.setInt(1, ++maxIndex);
                preparedStatement.setString(2, "Jack Black");
                preparedStatement.setDouble(3, 45000.00);
                preparedStatement.addBatch();

                preparedStatement.setInt(1, ++maxIndex);
                preparedStatement.setString(2, "Winnie The Pooh");
                preparedStatement.setDouble(3, 65000.00);
                preparedStatement.addBatch();

                preparedStatement.setInt(1, ++maxIndex);
                preparedStatement.setString(2, "Elon Musk");
                preparedStatement.setDouble(3, 85000.00);
                preparedStatement.addBatch();

                // Выполняем batch
                int[] updateCounts = preparedStatement.executeBatch();

                // Обрабатываем результаты
                for (int updateCount : updateCounts) {
                    if(updateCount > 0)
                        System.out.println("INSERT прошёл успешно");
                    else
                        System.out.println("INSERT не прошёл");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
