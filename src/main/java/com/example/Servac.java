package com.example;

import com.example.Essence;
import com.example.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Servac {

    private final CustomerRepository repository;


    public Servac(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Essence> getAllEssence() {
        return repository.findAll();
    }
}