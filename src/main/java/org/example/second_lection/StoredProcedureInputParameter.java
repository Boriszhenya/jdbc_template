package org.example.second_lection;

import java.sql.*;

public class StoredProcedureInputParameter {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/world";
        String username = "root";
        String password = "admin";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            // Подготовка вызова хранимой процедуры с входным параметром
            String storedProcedureCall = "{CALL getEmployeeInfoById(?)}";
            try (CallableStatement callableStatement = connection.prepareCall(storedProcedureCall)) {
                // Устанавливаем входной параметр (employee ID)
                int employeeId = 7;
                callableStatement.setInt(1, employeeId);

                // Вызываем хранимую процедуру
                ResultSet resultSet = callableStatement.executeQuery();

                while (resultSet.next()) {
                    // Получаем выходные значения
                    int id = resultSet.getInt(1);
                    String empName = resultSet.getString(2);
                    double empSalary = resultSet.getDouble(3);

                    // Какой-то процессинг результатов
                    System.out.println("Employee ID: " + id);
                    System.out.println("Employee Name: " + empName);
                    System.out.println("Employee Salary: " + empSalary);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
