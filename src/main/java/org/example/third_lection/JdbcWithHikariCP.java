package org.example.third_lection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcWithHikariCP {

    public static void main(String[] args) {
        // Загружаем HikariCP конфигурацию
        HikariConfig config = new HikariConfig("src/main/resources/hikari.properties");
        HikariDataSource dataSource = new HikariDataSource(config);

        try (Connection connection = dataSource.getConnection()) {
            // Используем соединение для создания запроса
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM world.employees");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // Закрываем объект пула при завершении работы с приложением!
            dataSource.close();
        }
    }
}
