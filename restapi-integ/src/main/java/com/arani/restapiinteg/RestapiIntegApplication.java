package com.arani.restapiinteg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;

@EnableIntegration
@SpringBootApplication
public class RestapiIntegApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestapiIntegApplication.class, args);
    }

}
