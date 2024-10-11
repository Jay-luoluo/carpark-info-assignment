package com.flinform.carpark.config;

import com.flinform.carpark.entity.CarPark;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
//import org.springframework.batch.item.file.transform.FlatFileItemReader;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
//import org.springframework.batch.item.file.support.SimpleResource;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@EnableBatchProcessing
@Configuration
public class BatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public BatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public FlatFileItemReader<CarPark> reader() {
        FlatFileItemReader<CarPark> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("hdb-carpark-information-20220824010400.csv")); // Path to your CSV file
        reader.setLineMapper(new DefaultLineMapper<CarPark>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("carParkNo", "address", "xCoord", "yCoord", "carParkType", "typeOfParkingSystem",
                        "shortTermParking", "freeParking", "nightParking", "carParkDecks", "gantryHeight", "carParkBasement");
            }});
            setFieldSetMapper(new FieldSetMapper<CarPark>() {
                @Override
                public CarPark mapFieldSet(FieldSet fieldSet) {
                    CarPark carPark = new CarPark();
                    carPark.setCarParkNo(fieldSet.readString("carParkNo"));
                    carPark.setAddress(fieldSet.readString("address"));
                    carPark.setXCoord(fieldSet.readDouble("xCoord"));
                    carPark.setYCoord(fieldSet.readDouble("yCoord"));
                    carPark.setCarParkType(fieldSet.readString("carParkType"));
                    carPark.setTypeOfParkingSystem(fieldSet.readString("typeOfParkingSystem"));
                    carPark.setShortTermParking(fieldSet.readString("shortTermParking"));
                    carPark.setFreeParking(fieldSet.readString("freeParking"));
                    carPark.setNightParking(fieldSet.readString("nightParking"));
                    carPark.setCarParkDecks(fieldSet.readInt("carParkDecks"));
                    carPark.setGantryHeight(fieldSet.readDouble("gantryHeight"));
                    carPark.setCarParkBasement(fieldSet.readString("carParkBasement"));
                    return carPark;
                }
            });
        }});
        return reader;
    }

    @Bean
    public JdbcBatchItemWriter<CarPark> writer(DataSource dataSource) {
        JdbcBatchItemWriter<CarPark> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        writer.setSql("INSERT INTO car_park (car_park_no, address, x_coord, y_coord, car_park_type, " +
                "type_of_parking_system, short_term_parking, free_parking, night_parking, " +
                "car_park_decks, gantry_height, car_park_basement) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        writer.setItemPreparedStatementSetter((carPark, ps) -> {
            ps.setString(1, carPark.getCarParkNo());
            ps.setString(2, carPark.getAddress());
            ps.setDouble(3, carPark.getXCoord());
            ps.setDouble(4, carPark.getYCoord());
            ps.setString(5, carPark.getCarParkType());
            ps.setString(6, carPark.getTypeOfParkingSystem());
            ps.setString(7, carPark.getShortTermParking());
            ps.setString(8, carPark.getFreeParking());
            ps.setString(9, carPark.getNightParking());
            ps.setInt(10, carPark.getCarParkDecks());
            ps.setDouble(11, carPark.getGantryHeight());
            ps.setString(12, carPark.getCarParkBasement());
        });
        return writer;
    }

    @Bean
    public Step importCarParkStep() {
        return stepBuilderFactory.get("importCarParkStep")
                .<CarPark, CarPark>chunk(10) // Process 10 records at a time
                .reader(reader())
                .writer(writer(null))
                .build();
    }

    @Bean
    public Job importCarParkJob() {
        return jobBuilderFactory.get("importCarParkJob")
                .incrementer(new RunIdIncrementer())
                .flow(importCarParkStep())
                .end()
                .build();
    }
}

