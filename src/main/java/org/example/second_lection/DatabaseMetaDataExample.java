package org.example.second_lection;

import java.sql.*;
import java.util.logging.Logger;

import static org.example.second_lection.BlobProcessing.*;
public class DatabaseMetaDataExample {
    public static void main(String[] args) {
        Logger logger = Logger.getGlobal();
        try (Connection connection = DriverManager.getConnection(
                jdbcUrl, username, password)) {
            // Получение DatabaseMetaData
            DatabaseMetaData metaData = connection.getMetaData();

            // Отображение некоторой информации
            System.out.println("Database Product Name: " + metaData.getDatabaseProductName());
            System.out.println("Database Product Version: " + metaData.getDatabaseProductVersion());
            System.out.println("Driver Name: " + metaData.getDriverName());
            System.out.println("Driver Version: " + metaData.getDriverVersion());

            // Больше методов
            ResultSet catalogs = metaData.getCatalogs();
            String sqlWords = metaData.getSQLKeywords();
            System.out.println("Supported SQL words: " + sqlWords);
            System.out.println("Catalogs/Schemas:");
            while (catalogs.next()){
                System.out.println("\t" + catalogs.getString(1));
                ResultSet tables = metaData.getTables(catalogs.getString(1), "%", "%", new String[]{"TABLE"});
                while (tables.next()) {
                    System.out.println("\t\t" + tables.getString(3));
                    ResultSet columns = metaData.getColumns(catalogs.getString(1), "%", tables.getString(3), "%");
                    while (columns.next()) {
                        System.out.println("\t\t\t" + columns.getString(4) + " - " + columns.getString(6));
                    }
                }
            }
            // Помните, что также можно получить дополнительные данные о таблицах
            // Колонках, индексах, и т.д. если посмотреть в конспект
        } catch (SQLException e) {
            logger.severe("Error during SQL execution");
            e.printStackTrace();
        }
    }
}
