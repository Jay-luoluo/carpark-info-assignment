package com.flinform.carpark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableJpaRepositories(basePackages = "com.flinform.carpark.repository")
public class CarParkApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarParkApplication.class, args);
    }

}
