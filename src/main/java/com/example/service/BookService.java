package com.example.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;


@Service
public class BookService {
    

    private final JdbcTemplate jdbcTemplate;

    public BookService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void reserveBook(Integer bookId, Integer readerId) {
        String sql = "INSERT INTO reservation (book_id, reader_id, get_date, promise_date) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, (rs) -> {
            rs.setInt(1, bookId);
            rs.setInt(2, readerId);
            rs.setDate(3, new Date(System.currentTimeMillis()));
            rs.setDate(4, new Date(System.currentTimeMillis() + 15L * 24 * 60 * 60 * 1000));
        });
    }

    public void returnBook(Integer  bookId, Integer readerId) {
        String sql = "UPDATE reservation SET return_date = ? " + "WHERE reader_id = ? and book_id = ?";
        jdbcTemplate.update(sql,(rs) -> {
            rs.setDate(1, new Date(System.currentTimeMillis()));
            rs.setInt(2, readerId);
            rs.setInt(3,bookId);
        });
    }
}













