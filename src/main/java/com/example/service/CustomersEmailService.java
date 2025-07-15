package com.example.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class CustomersEmailService {

    private final JdbcTemplate jdbcTemplate ;

    public CustomersEmailService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener(ApplicationReadyEvent.class)
     public void findAllEmail() {
         String sql = "SELECT * FROM customers";
         jdbcTemplate.query(sql, (rs) ->{
        String email = rs.getString("email");
        System.out.println(", Email: " + email);
    });
}}



