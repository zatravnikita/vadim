BEGIN;

CREATE TABLE customers (
                           customer_id SERIAL PRIMARY KEY,
                           name TEXT,
                           email TEXT,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO customers (name, email) VALUES
                                        ('Иван Иванов', 'ivanov@gmail.com'),
                                        ('Мария Петрова', 'petrova@gmail.com'),
                                        ('Алексей Смирнов', 'smirnov@gmail.com'),
                                        ('Ольга Кузнецова', 'kuznetsova@gmail.com'),
                                        ('Дмитрий Лебедев', 'lebedev@gmail.com'),
                                        ('Елена Новикова', 'novikova@gmail.com'),
                                        ('Сергей Морозов', 'morozov@gmail.com'),
                                        ('Анна Воронова', 'voronova@gmail.com'),
                                        ('Михаил Федоров', 'fedorov@gmail.com'),
                                        ('Наталья Соколова', 'sokolova@gmail.com'),
                                        ('Владимир Григорьев', 'grigoryev@gmail.com'),
                                        ('Татьяна Иванова', 'ivanova@gmail.com'),
                                        ('Андрей Ковалев', 'kovalev@gmail.com'),
                                        ('Юлия Дмитриева', 'dmitrieva@gmail.com'),
                                        ('Павел Белов', 'belov@gmail.com'),
                                        ('Ирина Васильева', 'vasileva@gmail.com'),
                                        ('Константин Попов', 'popov@gmail.com'),
                                        ('Марина Алексеева', 'alexejeva@gmail.com'),
                                        ('Роман Кузнецов', 'kuznetsov2@gmail.com'),
                                        ('Екатерина Михайлова', 'mikhaylova@gmail.com'),
                                        ('Александр Петров', 'petrov2@gmail.com'),
                                        ('Олеся Григорьева', 'grigorieva@gmail.com'),
                                        ('Денис Сидоров', 'sidorov@gmail.com'),
                                        ('Ксения Лебедева', 'lebedova@gmail.com'),
                                        ('Вячеслав Фролов', 'frolov@gmail.com');


CREATE TABLE dishes (
                        dish_id SERIAL PRIMARY KEY,
                        dish_name TEXT,
                        description TEXT,
                        price NUMERIC(6, 2),
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO dishes (dish_name, description, price) VALUES
                                                       ('Пицца Маргарита', 'Классическая пицца с томатным соусом, моцареллой и базиликом', 600.00),
                                                       ('Пицца Пепперони', 'Пицца с томатным соусом, моцареллой и колбасой пепперони', 700.00),
                                                       ('Пицца Гавайи', 'Пицца с ветчиной, ананасами и сыром', 650.00),
                                                       ('Пицца Вегетариана', 'Пицца с овощами: перец, помидоры, оливки и лук', 620.00),
                                                       ('Пицца Четыре сыра', 'Пицца с моцареллой, пармезаном, горгонзолой и рикоттой', 680.00),
                                                       ('Пицца Мясная', 'Пицца с мясным ассорти: колбаса, бекон, ветчина и курица', 750.00),
                                                       ('Пицца с морепродуктами', 'Пицца с креветками, мидиями и кальмарами', 800.00),
                                                       ('Пицца Барбекю', 'Пицца с соусом барбекю, курицей и луком', 700.00),

                                                       ('Пицца ветчиная','Пицца с ветчиной, грибами, оливками и артишоками', 700.00),
                                                       ('Пицца морская', 'Пицца с морепродуктами, помидорами и зеленью', 820.00),
                                                       ('Пицца классная','Пицца с колбасой, перцем, луком и сыром', 720.00),
                                                       ('Пицца норм','Пицца Ветчина и грибы', 680.00),

                                                       ('Пицца Песто', 'Пицца с соусом песто, моцареллой и помидорами черри', 690.00),
                                                       ('Пицца Баварская', 'Пицца с беконом, луком и сыром чеддер', 730.00),
                                                       ('Пицца Тоскана', 'Пицца с салями, оливками и руколой', 750.00),
                                                       ('Пицца Веганская', 'Пицца с овощами, без сыра, на растительной основе', 640.00);


CREATE TABLE sales (
                       sale_id SERIAL PRIMARY KEY,
                       dish_id INTEGER,
                       customer_id INTEGER,
                       quantity INTEGER,
                       total_amount NUMERIC(6, 2),
                       sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       CONSTRAINT fk_dish FOREIGN KEY (dish_id) REFERENCES dishes(dish_id) ,
                       CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);


INSERT INTO sales (dish_id, customer_id, quantity, total_amount, sale_date) VALUES

                                                                                (1, 1, 2, 500.00, '2024-04-25 12:30:00'),
                                                                                (2, 2, 1, 300.00, '2024-04-25 13:15:00'),
                                                                                (3, 3, 3, 1050.00, '2024-04-25 14:45:00'),
                                                                                (4, 4, 1, 400.00, '2024-04-25 15:20:00'),
                                                                                (5, 5, 2, 900.00, '2024-04-25 12:10:00'),
                                                                                (6, 6, 1, 800.00,'2024-04-25 13:50:00'),
                                                                                (7, 7, 4, 600.00, '2024-04-25 16:05:00'),
                                                                                (8, 8, 5, 750.00, '2024-04-25 17:30:00'),
                                                                                (9, 9, 6, 850.00, '2024-04-25 18:10:00'),
                                                                                (10, 10, 7, 950.00,'2024-04-25 19:45:00'),
                                                                                (11, 11, 8, 650.00,'2024-04-25 20:20:00'),
                                                                                (12, 12, 9, 700.00,'2024-04-25 21:15:00'),
                                                                                (13, 13, 10, 800.00,'2024-04-25 22:05:00'),
                                                                                (14, 14, 11, 900.00,'2024-04-25 22:30:00'),
                                                                                (15, 15, 12, 1000.00,'2024-04-26 23:10:00'),
                                                                                (16, 16, 13, 1100.00,'2024-04-26 23:45:00');
COMMIT;
