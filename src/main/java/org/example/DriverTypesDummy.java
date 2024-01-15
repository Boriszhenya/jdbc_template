package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverTypesDummy {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Type 1: JDBC-ODBC Bridge Driver
        // Load the JDBC-ODBC Bridge driver
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        // Create a connection using the JDBC-ODBC Bridge
        Connection connection = DriverManager.getConnection("jdbc:odbc:your_odbc_datasource");

        // Type 2: Native-API Driver
        // Load the Native-API driver
        Class.forName("com.mysql.jdbc.Driver");
        // Create a connection using the Native-API driver
        Connection connection2 = DriverManager.getConnection("jdbc:mysql://your_database_url", "username", "password");

        // Type 3: Network Protocol Driver
        // Load the Type 3 driver
        Class.forName("com.yourmiddleware.Driver");
        // Create a connection using the Type 3 driver
        Connection connection3 = DriverManager.getConnection("jdbc:yourmiddleware:your_database_url", "username", "password");

        // Type 4: Thin Driver
        // Load the Thin driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Create a connection using the Thin driver
        Connection connection4 = DriverManager.getConnection("jdbc:mysql://your_database_url", "username", "password");
    }
}
