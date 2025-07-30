package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class DadataConfig {

    @Value("${dadata.api.url}")
    private String dadataUrl;

    @Value("${dadata.token}")
    private String dadataToken;

    @Bean
    public WebClient dadataWebClient() {
        return WebClient.builder()
                .baseUrl(dadataUrl)
                .defaultHeader("Authorization", "Token " + dadataToken)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}

