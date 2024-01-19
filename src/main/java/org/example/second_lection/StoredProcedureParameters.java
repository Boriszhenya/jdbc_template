package org.example.second_lection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class StoredProcedureParameters {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/world?autoReconnect=true&connectTimeout=5000";
        String username = "root";
        String password = "admin";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            // Подготовка вызова хранимой процедуры с входными и выходными параметрами
            String storedProcedureCall = "{CALL getEmployeeById(?, ?, ?)}";
            try (CallableStatement callableStatement = connection.prepareCall(storedProcedureCall)) {
                // Устанавливаем входной параметр (employee ID)
                int employeeId = 1;
                callableStatement.setInt(1, employeeId);

                // Регистрируем выходные параметры (employee name и salary)
                callableStatement.registerOutParameter(2, Types.VARCHAR);
                callableStatement.registerOutParameter(3, Types.DECIMAL);

                // Выполняем хранимую процедуру
                callableStatement.execute();

                // Получаем значения выходных параметров
                String empName = callableStatement.getString(2);
                double empSalary = callableStatement.getDouble(3);

                // Какой-то процессинг результатов
                System.out.println("Employee ID: " + employeeId);
                System.out.println("Employee Name: " + empName);
                System.out.println("Employee Salary: " + empSalary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
