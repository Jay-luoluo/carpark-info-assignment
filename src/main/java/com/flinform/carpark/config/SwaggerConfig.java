package com.flinform.carpark.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.regex.Pattern;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.any())
                    .build()
                    .pathMapping("/");
                    //.ignoreResourcePaths(Pattern.compile("/error"));
    }

    // Optimized method to build ApiInfo
    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Car Park Management API", // Title of the API
                "API documentation for managing car parks", // Description
                "1.0",  // Version
                "Free to use",  // Terms of Service
                "", // Contact details
                "API License",  // License
                "http://www.example.com/license"  // License URL
                // Vendor extensions
        );
    }
}


