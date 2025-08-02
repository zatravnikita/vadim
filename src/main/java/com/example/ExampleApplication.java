package com.example;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ExampleApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(ExampleApplication.class, args);
    }
}