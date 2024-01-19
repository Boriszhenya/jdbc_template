package org.example.second_lection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCConfiguration {

    public static void main(String[] args) {
        Properties properties = loadProperties("src/main/resources/db.properties");

        if (properties != null) {
            // Получаем свойста из файла конфигурации
            String jdbcUrl = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");

            // Используем их для создания JDBC connection
            establishJDBCConnection(jdbcUrl, user, password);
        } else {
            System.out.println("Не получилось загрузить конфигурацию. Проверьте путь.");
        }
    }

    private static Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void establishJDBCConnection(String jdbcUrl, String user, String password) {
        try {
            // Устанавливаем JDBC connection
            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
            // Делаем что-то с БД
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM employees;");
            while (resultSet.next())
                System.out.println(resultSet.getInt(1) + ": " + resultSet.getString(2) + " $" + resultSet.getDouble(3));
            // Закрываем соединение
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
