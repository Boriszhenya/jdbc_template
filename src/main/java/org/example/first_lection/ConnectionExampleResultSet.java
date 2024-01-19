package org.example.first_lection;

import java.sql.*;

public class ConnectionExampleResultSet {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306";
        String user = "root";
        String password = "admin";

        try {
            // Регистрация MySQL JDBC driver (не всегда требуется)
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Установка соединения
            Connection connection = DriverManager.getConnection(url, user, password);
            // Чтобы работало надо выключить автокоммит //connection.setAutoCommit(false);
            //Savepoint savepoint = connection.setSavepoint();
            // Делаем что-то с БД
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM world.city WHERE Population = (SELECT MAX(Population) FROM world.city);");
            while (resultSet.next()){
                int id = resultSet.getInt("ID");
                String cityName = resultSet.getString(2);
                int population = resultSet.getInt("Population");
                System.out.printf("ID: %d; City: %s; Population: %d.%n", id, cityName, population);
            }
            // Очистить память занятую результатами запроса
            statement.close();
            //connection.rollback(savepoint);
            // Не забываем закрывать соединение когда закончили операции с БД
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

