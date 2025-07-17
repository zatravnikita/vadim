package com.example.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class CustomersService {

    private final JdbcTemplate jdbcTemplate;

    public CustomersService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void findAll() {
        String sql = "SELECT * FROM customers";
        jdbcTemplate.query(sql, (rs) -> {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
        });
    }
}



