package org.example;

import java.sql.*;

public class StatementVsPrepared {
    static String url = "jdbc:mysql://localhost:3306";
    static String user = "root";
    static String password = "admin";

    public static void main(String[] args) {
        // Это максимально что удалось придумать
        // TODO: надо закончить
        String injection = "Maharashtra' UNION ALL SELECT CountryCode, Language, IsOfficial, Percentage, NULL AS extraColumn FROM world.countrylanguage WHERE IsOfficial = 'T";
        //statementExecution("Maharashtra");
        //preparedStatementExecution("Maharashtra");
        statementExecution(injection);
        //preparedStatementExecution(injection);
    }

    static void statementExecution(String parameter){
        try {
            // Установка соединения
            Connection connection = DriverManager.getConnection(url, user, password);
            // Statement
            String query = "SELECT * FROM world.city WHERE District = '" + parameter + "' LIMIT 5";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
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
            while (resultSet.next()){
                System.out.println(resultSet.getInt("ID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void preparedStatementExecution(String parameter){
        try {
            // Установка соединения
            Connection connection = DriverManager.getConnection(url, user, password);
            // PreparedStatement
            String query = "SELECT * FROM world.city WHERE District = ? LIMIT 5";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, parameter);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getInt("ID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
