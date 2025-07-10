package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;


public class SimpleQuery {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/vadim"; // замените на ваши параметры
        String user = "vadim_user"; // замените на ваше имя пользователя
        String password = "1111"; // замените на ваш пароль

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM customers"; // ваш запрос
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


