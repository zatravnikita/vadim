package com.example.controller;

import com.example.service.BookService;
import com.example.service.UsersService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;

@RolesAllowed("USER")
@RestController
public class UserBookController {

    private final BookService bookService;
    private final UsersService userService;

    public UserBookController(BookService bookService, UsersService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping("user/book/active_reserve")
    public List<Map<String, Object>> getActiveReservedBooks() {
        return bookService.getUsersBook();
    }
}