package org.example;

import java.sql.*;

public class ConnectionExampleStoredProcedure {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/world";
        String user = "root";
        String password = "admin";

        try {
            // Регистрация MySQL JDBC driver (не всегда требуется)
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Установка соединения
            Connection connection = DriverManager.getConnection(url, user, password);
            // Создаём вызов хранимой процедуры
            CallableStatement statement = connection.prepareCall("{ CALL GetCityWithMaxPopulation() }");
            // Получаем результат выполнения
            ResultSet resultSet = statement.executeQuery();
            // Пока в результате есть строки
            while (resultSet.next()){
                int id = resultSet.getInt("ID");
                String cityName = resultSet.getString("Name");
                int population = resultSet.getInt("Population");
                System.out.printf("ID: %d; City: %s; Population: %d.%n", id, cityName, population);
            }
            // Не забываем закрывать соединение когда закончили операции с БД
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

