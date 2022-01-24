package com.ameed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CalculatorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalculatorServiceApplication.class, args);
    }

}
