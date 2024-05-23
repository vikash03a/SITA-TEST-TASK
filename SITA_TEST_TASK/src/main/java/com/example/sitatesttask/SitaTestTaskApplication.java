package com.example.sitatesttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableIntegration
public class SitaTestTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(SitaTestTaskApplication.class, args);
    }
}
