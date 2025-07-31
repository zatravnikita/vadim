package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class AddressConfig {

    @Value("${dadata.api.url}")
    private String dadataUrl;

    @Value("${dadata.api.token}")
    private String token;

    @Value("${dadata.api.secret}")
    private String secret;

    @Bean
    public WebClient dadataWebClient() {
        return WebClient.builder()
                .baseUrl(dadataUrl)
                .defaultHeader("Authorization", "Token " + token)
                .defaultHeader("X-Secret", secret)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}


