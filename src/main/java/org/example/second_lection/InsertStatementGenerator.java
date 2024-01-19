package org.example.second_lection;

import com.github.javafaker.Faker;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InsertStatementGenerator {

    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("en-US"));

        try (FileWriter fileWriter = new FileWriter("insert_statements.sql")) {
            for (int i = 1; i <= 10000; i++) {
                String insertStatement = generateInsertStatement(faker, i);
                fileWriter.write(insertStatement + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateInsertStatement(Faker faker, int id) {
        String name = faker.name().fullName().replace("'", "''");
        String description = faker.lorem().sentence().replace("'", "''");
        int age = faker.number().numberBetween(18, 65);
        double salary = faker.number().randomDouble(2, 30000, 100000);
        Date birthdate = faker.date().birthday();
        boolean married = faker.bool().bool();
        long oneYearAgoMillis = System.currentTimeMillis() - (365L * 24 * 60 * 60 * 1000); // milliseconds in a year
        long createdAtMillis = oneYearAgoMillis + (long) (Math.random() * (System.currentTimeMillis() - oneYearAgoMillis));
        SimpleDateFormat birthdateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("INSERT INTO big_table (id, name, description, age, salary, birthdate, married, created_at) " +
                        "VALUES (%d, '%s', '%s', %d, %.2f, '%s', %b, '%s');",
                id, name, description, age, salary, birthdateFormat.format(birthdate), married, new Timestamp(createdAtMillis));
    }
}
