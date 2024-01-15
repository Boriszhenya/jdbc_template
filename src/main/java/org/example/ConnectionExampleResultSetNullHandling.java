package org.example;

import java.sql.*;

public class ConnectionExampleResultSetNullHandling {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306";
        String user = "root";
        String password = "admin";

        try {
            // Регистрация MySQL JDBC driver (не всегда требуется)
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Установка соединения
            Connection connection = DriverManager.getConnection(url, user, password);
            // Делаем что-то с БД
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM world.city WHERE ID < 10;");
            while (resultSet.next()) {
                String districtValue = resultSet.getString("District");
                if(resultSet.wasNull())
                    System.out.println("Строка с пустым District");
                else {
                    int id = resultSet.getInt("ID");
                    String cityName = resultSet.getString("Name");
                    int population = resultSet.getInt("Population");
                    System.out.printf("ID: %d; City: %s; Population: %d.%n", id, cityName, population);
                }
            }
            // Если закончили работать с ResultSet раньше, чем с его родительской сущностью - закрываем
            resultSet.close();
            // Не забываем закрывать соединение когда закончили операции с БД
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

