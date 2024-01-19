package org.example.second_lection;

import java.sql.*;
import java.util.logging.Logger;

import static org.example.second_lection.BlobProcessing.*;

public class CommitRollbackExample {
    public static void main(String[] args) {
        Logger logger = Logger.getGlobal();
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "INSERT INTO world.employees (employee_id, employee_name, salary) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Выключаем автокоммит
                connection.setAutoCommit(false);
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
                preparedStatement.setString(3, "Biggest salary");
                preparedStatement.addBatch();
                try {
                    // Выполняем batch
                    int[] updateCounts = preparedStatement.executeBatch();
                    // Выполняем коммит - если будут ошибки, сюда не дойдёт
                    connection.commit();
                    logger.info("BATCH прошёл успешно. Изменения закоммичены");
                    // Обрабатываем результаты
                    for (int updateCount : updateCounts) {
                        if (updateCount > 0)
                            logger.info("INSERT прошёл успешно");
                        else
                            logger.info("INSERT не прошёл");
                    }
                } catch (SQLException e) {
                    logger.severe("BATCH не прошёл с ошибкой: " + e.getMessage());
                    // Откатываем в случае ошибки
                    connection.rollback();
                    logger.severe("BATCH откачен!");
                } finally {
                    // Восстанавлиаем автокоммит
                    connection.setAutoCommit(true);
                }
            }
        } catch (SQLException e) {
            logger.severe("BATCH не прошёл до запроса с ошибкой: " + e.getMessage());
        }
    }
}
