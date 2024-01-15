package org.example;

import java.sql.*;

public class ConnectionExamplePreparedStatement {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/world";
        String user = "root";
        String password = "admin";

        // Параметры для вызова подготовленной процедуры - может быть параметром метода
        int cityIdToFind = 1024;
        String disctrictNameToFind = "Maharashtra";
        // Подключаемся к БД прям в try-catch блоке
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // Создаём PreparedStatement с параметром
            String sql = "SELECT * FROM world.city WHERE ID = ? OR District = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Устанавливаем значение параметров (индексы с 1 идут)
                preparedStatement.setInt(1, cityIdToFind);
                preparedStatement.setString(2, disctrictNameToFind);
                // Выполняем запрос
                ResultSet resultSet = preparedStatement.executeQuery();
                // Процессинг результата
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String name = resultSet.getString("Name");
                    String countryCode = resultSet.getString("CountryCode");
                    String district = resultSet.getString("District");
                    int population = resultSet.getInt("Population");

                    // Делаем что-то с результатом
                    System.out.println("ID: " + id + ", Name: " + name + ", CountryCode: " + countryCode +
                            ", District: " + district + ", Population: " + population);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

