package com.example.service;

import com.example.dto.BookReserveDto;
import com.example.dto.BookReturnDto;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.sql.Date;
import java.util.List;
import java.util.Map;


@Service

public class BookService {


    private final JdbcTemplate jdbcTemplate;
    private final UsersService userService;


    public BookService(JdbcTemplate jdbcTemplate, UsersService userService) {
        this.jdbcTemplate = jdbcTemplate;
        this.userService = userService;
    }

    public void returnBook(BookReturnDto bookReturnDto) {
        String sqlReturn = "UPDATE reservation SET return_date = ? FROM public.books_instance bi, public.readers rea " +
                "WHERE reservation.books_instance_id = bi.id AND reservation.users_id = rea.id AND bi.number = ? AND rea.number = ?";
        jdbcTemplate.update(sqlReturn, ps -> {
            ps.setDate(1, new Date(System.currentTimeMillis()));
            ps.setString(2, bookReturnDto.getBookNumber());
            ps.setString(3, bookReturnDto.getReaderNumber());
        });
    }

    public void reserveBook(BookReserveDto bookReserveDto) {
        String sqlCheck = "SELECT COUNT(*) FROM reservation r JOIN books_instance b ON r.books_instance_id = b.id WHERE b.number = ? AND r.return_date IS NULL";
        Integer reserveCheck = jdbcTemplate.queryForObject(sqlCheck, Integer.class, bookReserveDto.getBookNumber());
        if (reserveCheck == 0) {
            String sqlInsert = "INSERT INTO reservation (books_instance_id, users_id, get_date, promise_date) " +
                    "SELECT bi.id, r.id, ?, ? FROM books_instance bi, public.users r WHERE bi.number = ? AND r.number = ?";
            jdbcTemplate.update(sqlInsert, ps -> {
                ps.setDate(1, new java.sql.Date(System.currentTimeMillis()));
                ps.setDate(2, java.sql.Date.valueOf(bookReserveDto.getPromiseDate()));
                ps.setString(3, bookReserveDto.getBookNumber());
                ps.setString(4, bookReserveDto.getReaderNumber());
            });
        } else {
            throw new IllegalStateException("Книга уже зарезервирована");
        }
    }

    public List<Map<String, Object>> getUsersBook() {
        Integer userId = userService.getCurrentUserId();
        return jdbcTemplate.queryForList(
                "SELECT b.author, b.work, r.promise_date  FROM reservation r JOIN books_instance bi ON r.books_instance_id = bi.id JOIN books b ON bi.book_id = b.id JOIN users s on r.users_id = s.id  WHERE s.id = ? AND r.return_date IS NULL",
                userId
        );
    }


}

