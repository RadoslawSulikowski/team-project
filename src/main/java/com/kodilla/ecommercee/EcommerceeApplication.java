package com.kodilla.ecommercee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class EcommerceeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceeApplication.class, args);
    }

}

