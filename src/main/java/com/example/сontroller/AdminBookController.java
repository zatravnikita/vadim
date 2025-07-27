package com.example.сontroller;

import com.example.dto.BookReserveDto;
import com.example.dto.BookReturnDto;
import com.example.service.BookService;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RolesAllowed("USER")
    @PutMapping("/book/return")
    public void returnBook(@RequestBody BookReturnDto bookReturnDto) {
        bookService.returnBook(bookReturnDto);
    }


    @PostMapping("/book/reserv")
    public void reserveBook(@RequestBody BookReserveDto bookReserveDto) {
         bookService.reserveBook(bookReserveDto);
    }
}