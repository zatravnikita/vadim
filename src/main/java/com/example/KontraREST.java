package com.example;

import com.example.Essence;
import com.example.Servac;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class KontraREST {

    private final Servac service;

    public KontraREST(Servac service) {
        this.service = service;
    }

    @GetMapping("/Essence")
    public List<Essence> getEssence() {
        return service.getAllEssence();
    }
}