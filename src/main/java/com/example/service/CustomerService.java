package com.example.service;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
public class CustomerService {

    @EventListener(ApplicationReadyEvent.class)
    public void findAll() {
        String url = "jdbc:postgresql://localhost:5432/vadim";
        String user = "vadim_user";
        String password = "1111";

        try (Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM customers";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("customer_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}