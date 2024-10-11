package com.flinform.carpark.service;

import com.flinform.carpark.entity.CarPark;
import com.opencsv.CSVReader;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManagerFactory;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Processor {
    private static final String CSV_FILE_PATH = "D:\\ideaproject\\interview\\carpark-info-assignment\\hdb-carpark-information-20220824010400.csv";
    private static final String DB_URL = "jdbc:h2:tcp://192.168.0.131:9092/~/test";  // Replace with your DB URL
    private static final String USER = "sa";                                         // Replace with your DB username
    private static final String PASSWORD = "sa";                                 // Replace with your DB password


    public static void main(String[] args) {
        importDataFromCSV();
    }

    private static void importDataFromCSV() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            connection.setAutoCommit(false);
            // Step 2: Read CSV file using OpenCSV
            try (CSVReader csvReader = new CSVReader(new FileReader(CSV_FILE_PATH))) {
                String[] values;
                // Skip the header line if necessary
                csvReader.readNext();  // Uncomment if your CSV has headers

                // Prepare the SQL insert statement
                String insertQuery = "INSERT INTO car_park (car_park_no, address, x_coord, y_coord, car_park_type, " + "type_of_parking_system, short_term_parking, free_parking, night_parking, car_park_decks, gantry_height, car_park_basement) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    // Step 3: Iterate through CSV rows and insert into the database
                    while ((values = csvReader.readNext()) != null) {
                        // Mapping each CSV column to the appropriate table column
                        setParameters(preparedStatement, values);

                        // Execute the query
                        preparedStatement.addBatch();  // Add to batch for efficiency
                    }

                    // Step 4: Execute batch insert
                    preparedStatement.executeBatch();
                    connection.commit();
                    System.out.println("Data successfully inserted into the database!");

                } catch (SQLException e) {
                    connection.rollback();
                    System.err.println("Error executing SQL query: " + e.getMessage());
                    e.printStackTrace();
                }
            } catch (Exception e) {
                System.out.println("Error reading CSV file");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }

    private static void setParameters(PreparedStatement preparedStatement, String[] values) throws SQLException {
        preparedStatement.setString(1, values[0]);  // car_park_no
        preparedStatement.setString(2, values[1]);  // address
        preparedStatement.setDouble(3, Double.parseDouble(values[2]));  // x_coord
        preparedStatement.setDouble(4, Double.parseDouble(values[3]));  // y_coord
        preparedStatement.setString(5, values[4]);  // car_park_type
        preparedStatement.setString(6, values[5]);  // type_of_parking_system
        preparedStatement.setString(7, values[6]);  // short_term_parking
        preparedStatement.setString(8, values[7]);  // free_parking
        preparedStatement.setString(9, values[8]);  // night_parking
        preparedStatement.setInt(10, Integer.parseInt(values[9]));  // car_park_decks
        preparedStatement.setDouble(11, Double.parseDouble(values[10]));  // gantry_height
        preparedStatement.setString(12, values[11]);  // car_park_basement
    }



}
