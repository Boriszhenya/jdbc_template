package org.example.first_lection;

import java.sql.*;

public class ConnectionExampleUpdate {
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
            int result = statement.executeUpdate("UPDATE world.city SET city.District = NULL WHERE Population = 42;");
            if(result > 0)
                System.out.println("Обновление данных прошло успешно");
            // Очистить память занятую результатами запроса
            statement.close();
            // Не забываем закрывать соединение когда закончили операции с БД
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

