package org.example.second_lection;

import java.io.*;
import java.sql.*;

public class BlobProcessing {
    static String jdbcUrl = "jdbc:mysql://localhost:3306/";
    static String username = "root";
    static String password = "admin";
    public static void main(String[] args) {
        //insertBlob(new File("src/main/resources/fine.jpg"));
        //insertBlob(new File("src/main/resources/Googling_cheatsheet.pdf"));
        writeBlobToFile(2);
    }

    public static void insertBlob(File inputFile) {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "INSERT INTO world.blob_table (id, data, file_name, data_type) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Вот это всё для того чтобы получить следующий ID
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM world.blob_table");
                int maxIndex = 0;
                while (resultSet.next())
                    maxIndex = resultSet.getInt(1);
                preparedStatement.setInt(1, maxIndex + 1);  // ID
                // Устанавливаем значение BLOB в колонку data
                InputStream inputStream = new FileInputStream(inputFile);
                preparedStatement.setBinaryStream(2, inputStream);
                // Пишем имя файла и расширение
                preparedStatement.setString(3, inputFile.getName().split("\\.")[0]);
                preparedStatement.setString(4, inputFile.getName().split("\\.")[1]);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeBlobToFile(int blobId) {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "SELECT * FROM world.blob_table WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, blobId);  // ID
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Получаем BLOB из колонки data
                        InputStream inputStream = resultSet.getBinaryStream("data");
                        // Процессинг BLOB-а (например, пишем в файл)
                        FileOutputStream fos = new FileOutputStream(resultSet.getString("file_name") + "." + resultSet.getString("data_type"));
                        fos.write(inputStream.readAllBytes());
                        fos.close();
                    }
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    // TODO: Написать апдейт блоба в таблице по ID
}
