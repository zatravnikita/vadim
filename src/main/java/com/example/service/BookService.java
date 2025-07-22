package com.example.service;



import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;


@Service
public class BookService {


    private final JdbcTemplate jdbcTemplate;

    public BookService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void returnBook(String readerIdReturn, String bookReturn) {
        String sql = "UPDATE reservation SET return_date = ? FROM public.books_instance bi, public.readers rea WHERE reservation.books_instance_id = bi.id AND reservation.reader_id = rea.id AND bi.number = ? AND rea.number = ?";
        jdbcTemplate.update(sql, ps -> {
            ps.setDate(1, new Date(System.currentTimeMillis()));
            ps.setString(2, readerIdReturn);
            ps.setString(3, bookReturn);
        });
    }

    public synchronized void reserveBook(String bookNumber, String readerId, LocalDate promiseDate) {
        String sqlCheck = "SELECT COUNT(*) FROM reservation r JOIN books_instance b ON r.books_instance_id = b.id WHERE b.number = ? AND r.return_date IS NULL";
        Integer reserveCheck = jdbcTemplate.queryForObject(sqlCheck, Integer.class, bookNumber);
        if (reserveCheck == 0) {
            String sqlInsert = "INSERT INTO reservation (books_instance_id, reader_id, get_date, promise_date)" +
                     "SELECT bi.id, rea.id, ?, ? FROM public.books_instance bi, public.readers rea WHERE bi.number = ? AND rea.number = ?";

            jdbcTemplate.update(sqlInsert, ps -> {
                ps.setDate(1, new java.sql.Date(System.currentTimeMillis()));
                ps.setDate(2, java.sql.Date.valueOf(promiseDate));
                ps.setString(3, bookNumber);
                ps.setString(4, readerId);
            });
        } else {
            throw new IllegalStateException("Книга уже зарезервирована");
        }
    }
}