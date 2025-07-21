package com.example.service;


import com.example.runner.AppRun;
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


    public void returnBook(Integer readerIdReturn, String authorBookReturn, String workBookReturn, Integer copyBookReturn) {
        String sql = "UPDATE reservation r SET return_date = ? FROM books b WHERE r.book_id = b.id AND r.reader_id = ? AND b.author = ? AND b.work = ? AND b.copy = ?";

        jdbcTemplate.update(sql, ps -> {
            ps.setDate(1, new Date(System.currentTimeMillis())); // return_date
            ps.setInt(2, readerIdReturn);
            ps.setString(3, authorBookReturn);
            ps.setString(4, workBookReturn);
            ps.setInt(5, copyBookReturn);
        });
    }

    public synchronized void reserveBook(String authorBook, String workBook,Integer copyBook ,LocalDate promiseDate, Integer readerId) {
        String sqlCheck = "SELECT COUNT(*) FROM reservation r JOIN books b ON r.book_id = b.id WHERE b.author = ? AND b.work = ? AND b.copy = ? AND r.return_date IS NULL";
        Integer reserveCheck = jdbcTemplate.queryForObject(sqlCheck, Integer.class, authorBook, workBook, copyBook);
        if (reserveCheck == 0) {
            String sqlInsert = "INSERT INTO reservation (book_id, reader_id, get_date, promise_date) " +
                    "SELECT b.id, ?, ?, ? FROM books b WHERE b.author = ? AND b.work = ? AND b.copy = ?";
            jdbcTemplate.update(sqlInsert, ps -> {
                ps.setInt(1, readerId);
                ps.setDate(2, new Date(System.currentTimeMillis()));
                ps.setDate(3, java.sql.Date.valueOf(promiseDate));
                ps.setString(4, authorBook);
                ps.setString(5, workBook);
                ps.setInt(6, copyBook );
            });
        } else {
            throw new IllegalStateException("Книга уже зарезервирована");
        }
    }


}

