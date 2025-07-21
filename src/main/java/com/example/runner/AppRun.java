package com.example.runner;


import com.example.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        System.out.print("1 - Вять книгу ");
        System.out.println("2 - Вернуть книгу");
        System.out.println("3 - Выход");

        do {
            x = scanner.nextInt();
            if (x == 1 || x == 2) {



                switch (x) {
                    case 1:

                        System.out.println("Введите Id читателя");
                        int readerId = scanner.nextInt();

                        scanner.nextLine();

                        System.out.println("Введите автора книги");
                        String authorBook = scanner.nextLine();

                        System.out.println("Введите название книги");
                        String workBook = scanner.nextLine();

                        System.out.println("Введите номер копии книги");
                        int copyBook = scanner.nextInt();


                        System.out.println("Введите дату обещанного возврата (в формате ГГГГ-ММ-ДД):");
                        String stringPromise = scanner.next();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate promiseDate = LocalDate.parse(stringPromise, formatter);

                        bookService.reserveBook(authorBook, workBook, copyBook, promiseDate, readerId);
                        System.out.println("Обновление данных прошло успешно");
                        break;

                    case 2:

                        System.out.println("Введите автора книги");
                        String authorBookReturn = scanner.next();
                        scanner.nextLine();

                        System.out.println("Введите название книги");
                        String workBookReturn = scanner.next();

                        System.out.println("Введите Id читателя");
                        int readerIdReturn = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Номер копии книги");
                        int copyBookReturn = scanner.nextInt();

                        bookService.returnBook(readerIdReturn, authorBookReturn, workBookReturn,  copyBookReturn);
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




