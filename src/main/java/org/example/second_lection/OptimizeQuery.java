package org.example.second_lection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import static org.example.second_lection.BlobProcessing.*;

public class OptimizeQuery {

    static Logger logger = Logger.getGlobal();
    public static void main(String[] args) {
        String unOptimalSQL = "SELECT * FROM world.big_table\n" +
                "    WHERE\n" +
                "        description LIKE \"%quos%\" AND\n" +
                "\t\tmarried IS NOT TRUE AND\n" +
                "        created_at < NOW() - '1 year' AND\n" +
                "        salary > 50000.00 AND\n" +
                "        age > 25 AND\n" +
                "        age < 35 AND\n" +
                "        id < 3000      \n" +
                "        ";
        String moreOptimalSQL = "SELECT * FROM world.big_table\n" +
                "    WHERE\n" +
                "\t\tid < 3000 AND\n" +
                "        married = 0 AND\n" +
                "        age > 25 AND\n" +
                "        age < 35 AND   \n" +
                "        salary > 50000.00 AND\n" +
                "        created_at < NOW() - '1 year' AND\n" +
                "        description LIKE \"%quos%\"\n";
        logger.info(String.format("Percentage difference is: %.2f %%", calculatePercentageDifference(getExecutionTime(unOptimalSQL), getExecutionTime(moreOptimalSQL))));
    }
    static long getExecutionTime(String query) {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            Statement statement = connection.createStatement();
            long start = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                statement.executeQuery(query);
            }
            long finish = System.currentTimeMillis();
            long result = finish-start;
            logger.info("Execution time is: " + result + " ms");
            return result;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return 0;
    }

    private static double calculatePercentageDifference(long value1, long value2) {
        if (value1 == 0) {
            throw new IllegalArgumentException("Cannot calculate percentage difference when the first value is zero.");
        }

        // Calculate percentage difference
        double difference = value1 - value2;
        double percentageDifference = (difference / Math.abs(value1)) * 100;

        return Math.abs(percentageDifference); // Absolute value to ensure a positive percentage
    }
}
