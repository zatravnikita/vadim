package com.example.Controller;
import com.example.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.sql.Date;
import java.time.LocalDate;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PutMapping("/book/return/{readerNumber}/{bookNumber}")
    public void returnBook(@PathVariable String readerNumber, @PathVariable String bookNumber) {
        bookService.returnBook(readerNumber, bookNumber);
    }
    @PostMapping("/book/reserv/{bookNumber}/{readerNumber}/{promiseDate}")
    public void reserveBook(@PathVariable String bookNumber, @PathVariable String readerNumber, @PathVariable LocalDate promiseDate) {
         bookService.reserveBook(bookNumber,readerNumber, promiseDate);

    }
}