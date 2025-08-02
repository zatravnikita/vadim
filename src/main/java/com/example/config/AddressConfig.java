package com.example.config;


import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddressConfig {

    @Bean
    public RequestInterceptor requestInterceptor(
            @Value("${dadata.api.token}") String token,
            @Value("${dadata.api.secret}") String secret) {

        return template -> {
            template.header("Authorization", "Token " + token);
            template.header("X-Secret", secret);
        };
    }
}