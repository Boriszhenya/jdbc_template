package org.example.first_lection;

import java.sql.*;

public class ConnectionExampleResultSetMetaData {
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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM world.city WHERE Population = (SELECT MAX(Population) FROM world.city);");
            // Получаем метаданные результата запроса
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            // Получаем количество столбцов в результате
            int columnCount = resultSetMetaData.getColumnCount();
            System.out.println("Количество столбцов: " + columnCount);
            // Обращаем внимание, что индексы идут с 1 а не с 0
            for (int i = 1; i <= columnCount; i++) {
                System.out.println(
                        "Столбец: " + resultSetMetaData.getColumnName(i) +
                        "; БД тип данных: " + resultSetMetaData.getColumnTypeName(i) +
                        "; Java тип данных: " + resultSetMetaData.getColumnClassName(i)
                );
            }
            // Не забываем закрывать соединение когда закончили операции с БД
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

