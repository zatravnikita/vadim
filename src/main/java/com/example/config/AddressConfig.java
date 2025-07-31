package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class DadataConfig {

    @Value("${dadata.api.url}")
    private String dadataUrl;

    @Value("${dadata.api.key}")
    private String dadataToken;

    @Value("${dadata.api.secret}")
    private String dadataTokenX;


    @Bean
    public WebClient dadataWebClient() {
        return WebClient.builder()
                .baseUrl(dadataUrl)
                .defaultHeader("Authorization", "Token " + dadataToken)
                .defaultHeader("Authorization", "Token " + dadataTokenX)
                .defaultHeader("Content-Type", "application/json")
                .build();

    }



}

