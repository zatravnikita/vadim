package com.example.service;

import com.example.dto.AddressResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


import java.util.Collections;
import java.util.List;

@Service
public class AddressService {

    private final WebClient webClient;

    public AddressService(WebClient dadataWebClient) {
        this.webClient = dadataWebClient;
    }

    public Mono<AddressResponse> cleanAddress(String query) {
        List<String> queries = Collections.singletonList(query);
        return webClient.post()
                .bodyValue(queries)
                .header("Content-Type", "application/json")
                .retrieve()
                .bodyToMono(AddressResponse[].class)
                .map(response -> response[0]);
    }
}
