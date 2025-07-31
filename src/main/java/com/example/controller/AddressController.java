package com.example.controller;

import com.example.service.AddressService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RolesAllowed("ADMIN")
@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/find")
    public Mono<String> findAddress(@RequestBody String query) {
        return addressService.cleanAddress(query)
                .map(address -> address.getResult());
    }
}