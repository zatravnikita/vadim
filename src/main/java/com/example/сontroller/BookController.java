package com.example.сontroller;
import com.example.service.BookService;

import org.springframework.web.bind.annotation.*;

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