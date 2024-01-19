package org.example.second_lection;

import java.sql.*;
import java.util.logging.Logger;

import static org.example.second_lection.BlobProcessing.*;

public class LoggingExceptionTryWithResources {
    public static void main(String[] args) {
        Logger logger = Logger.getGlobal();
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM your_table")) {
            // Процессинг ResultSet
        } catch (SQLException e) {
            logger.severe("Error during SQL execution: " + e.getMessage());
        }
    }
}
