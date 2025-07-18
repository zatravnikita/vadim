package com.example.runner;


import com.example.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Random;
import java.util.Scanner;


@Component
public class AppRun implements CommandLineRunner {

    private final BookService bookService;

    public AppRun(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int x;
        System.out.print("1 - Вернуть книгу ");
        System.out.println("2 - Взять книгу");
        System.out.println("3 - Выход");

        do {
            x = scanner.nextInt();
                if(x == 1 || x == 2) {
                System.out.println("Введи id книги");
                int bookId = scanner.nextInt();

                System.out.println("Введи id читателя");
                int readerId = scanner.nextInt();

                switch (x) {
                    case 1:
                        bookService.reserveBook(bookId, readerId);
                        System.out.println("Добавление новой записи прошло успешно");
                        break;
                    case 2:
                        bookService.returnBook(bookId, readerId);
                        System.out.println("Обновление данных прошло успешно");
                        break;
                    default:
                        System.out.println("Введите 1,2, 3");
                        break;
                }
            }
        } while (x != 3);
    }
}




