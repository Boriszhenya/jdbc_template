package org.example;

import javax.imageio.ImageReader;
import java.awt.*;
import java.sql.*;

public class ResultSetMethods {
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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM sakila.payment WHERE payment_id = 999;");
            while (resultSet.next()){
                int id = resultSet.getInt("payment_id");
                //BLOb - BLOB (Binary Large Object)
                //resultSet.getBlob("img");
                Double amount = resultSet.getDouble("amount");
                Date paymentDate = resultSet.getDate("payment_date");
                Time paymentTime = resultSet.getTime("payment_date");
                Timestamp lastUpdate = resultSet.getTimestamp("last_update");
                System.out.printf("Payment ID: %d; Amount: %.2f; Payment date: %s; Payment time: %s; Last update: %s.%n", id, amount, paymentDate, paymentTime, lastUpdate);
            }
            // Не забываем закрывать соединение когда закончили операции с БД
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
