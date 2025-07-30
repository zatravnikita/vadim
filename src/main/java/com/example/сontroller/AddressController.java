package com.example.сontroller;

import com.example.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@RestController
public class AddressController {

    private final BookService findAddress;

    public AddressController(BookService findAddress) {
        this.findAddress = findAddress;
    }

    @GetMapping("/address/search")
    public Mono<String> findAddress(@RequestParam String query) {
        return findAddress.findAddress(query);
    }
}
