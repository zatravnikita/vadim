package com.example.сontroller;

import com.example.dto.BookReserveDto;
import com.example.dto.BookReturnDto;
import com.example.service.BookService;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.*;


@RolesAllowed("ADMIN")
@RestController
public class AdminBookController {

    private final BookService bookService;

    public AdminBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PutMapping("admin/book/return")
    public void returnBook(@RequestBody BookReturnDto bookReturnDto) {
        bookService.returnBook(bookReturnDto);
    }

    @PostMapping("admin/book/reserv")
    public void reserveBook(@RequestBody BookReserveDto bookReserveDto) {
         bookService.reserveBook(bookReserveDto);
    }
}