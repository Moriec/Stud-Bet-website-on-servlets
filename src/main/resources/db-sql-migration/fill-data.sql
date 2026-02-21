-- ===== ВСТАВКА 15 ПОЛЬЗОВАТЕЛЕЙ =====
INSERT INTO users(username, email, first_name, last_name, password_hash, role, balance, rating_points) VALUES
('alex_student', 'alex@university.ru', 'Алексей', 'Иванов', '$2a$10$abc123def456ghi789jkl', 'STUDENT', 50000, 1200),
('maria_bet', 'maria@university.ru', 'Мария', 'Петрова', '$2a$10$xyz789uvw012qrs345tux', 'STUDENT', 75000, 2100),
('ivan_pro', 'ivan@university.ru', 'Иван', 'Сидоров', '$2a$10$def456ghi789jkl012mno', 'STUDENT', 120000, 3500),
('elena_sharp', 'elena@university.ru', 'Елена', 'Волкова', '$2a$10$ghi789jkl012mno345pqr', 'STUDENT', 45000, 980),
('nikita_swift', 'nikita@university.ru', 'Никита', 'Морозов', '$2a$10$jkl012mno345pqr678stu', 'STUDENT', 90000, 2800),
('sophia_ace', 'sophia@university.ru', 'София', 'Федорова', '$2a$10$mno345pqr678stu901vwx', 'STUDENT', 65000, 1900),
('dmitry_wise', 'dmitry@university.ru', 'Дмитрий', 'Кузнецов', '$2a$10$pqr678stu901vwx234yza', 'STUDENT', 110000, 3200),
('anna_luck', 'anna@university.ru', 'Анна', 'Орлова', '$2a$10$stu901vwx234yza567bcd', 'STUDENT', 55000, 1450),
('sergey_mind', 'sergey@university.ru', 'Сергей', 'Соколов', '$2a$10$vwx234yza567bcd890efg', 'STUDENT', 85000, 2600),
('victoria_star', 'victoria@university.ru', 'Виктория', 'Смирнова', '$2a$10$yza567bcd890efg123hij', 'STUDENT', 72000, 2200),
('pavel_bet', 'pavel@university.ru', 'Павел', 'Новиков', '$2a$10$bcd890efg123hij456klm', 'STUDENT', 95000, 2950),
('tatiana_gold', 'tatiana@university.ru', 'Татьяна', 'Лебедева', '$2a$10$efg123hij456klm789nop', 'STUDENT', 68000, 1750),
('andrew_thunder', 'andrew@university.ru', 'Андрей', 'Козлов', '$2a$10$hij456klm789nop012qrs', 'STUDENT', 78000, 2400),
('admin_user', 'admin@university.ru', 'Администратор', 'Системы', '$2a$10$klm789nop012qrs345tux', 'ADMIN', 1000000, 10000),
('yulia_flash', 'yulia@university.ru', 'Юлия', 'Воробьева', '$2a$10$nop012qrs345tux678vwx', 'STUDENT', 61000, 1600);

-- ===== ВСТАВКА 10 ПРЕДМЕТОВ =====
INSERT INTO subjects(subject_name, subject_code, description, max_score) VALUES
('Математический Анализ', 'MA-101', 'Основы математического анализа, пределы, производные, интегралы', 100),
('Линейная Алгебра', 'LA-102', 'Матрицы, векторные пространства, системы уравнений', 100),
('Дискретная Математика', 'DM-103', 'Логика, теория множеств, комбинаторика, теория графов', 100),
('Программирование на Java', 'JAVA-201', 'ООП, коллекции, многопоточность', 100),
('Структуры Данных', 'DS-202', 'Списки, стеки, очереди, деревья, графы', 100),
('Базы Данных', 'DB-203', 'SQL, нормализация, проектирование БД', 100),
('Веб-разработка', 'WEB-204', 'HTML, CSS, JavaScript, REST API', 100),
('Алгоритмы', 'ALG-205', 'Анализ сложности, сортировка, поиск, динамическое программирование', 100),
('Операционные Системы', 'OS-206', 'Процессы, потоки, память, файловые системы', 100),
('Архитектура Компьютеров', 'ARCH-207', 'Логические схемы, ассемблер, процессоры', 100);

-- ===== ВСТАВКА 10 СОБЫТИЙ СТАВОК =====
INSERT INTO betting_events(target_user_id, subject_id, semester, academic_year, event_type, status, description, created_at) VALUES
(1, 1, 1, '2024-2025', 'EXAM', 'OPEN', 'Семестр 1. На: Алексей Иванов. Экзамен: Математический анализ', NOW()),
(2, 2, 1, '2024-2025', 'EXAM', 'OPEN', 'Семестр 1. На: Мария Петрова. Экзамен: Линейная алгебра', NOW() - INTERVAL '2 days'),
(3, 3, 1, '2024-2025', 'ASSIGNMENT', 'OPEN', 'Семестр 1. На: Иван Сидоров. Экзамен: Дискретная математика', NOW() - INTERVAL '1 day'),
(4, 4, 2, '2024-2025', 'EXAM', 'CLOSED', 'Семестр 2. На: Елена Волкова. Экзамен: Программирование на Java', NOW() - INTERVAL '10 days'),
(5, 5, 2, '2024-2025', 'ASSIGNMENT', 'OPEN', 'Семестр 2. На: Никита Морозов. Экзамен: Структуры данных', NOW()),
(6, 6, 2, '2024-2025', 'EXAM', 'OPEN', 'Семестр 2. На: София Федорова. Экзамен: Базы данных', NOW() - INTERVAL '5 days'),
(7, 7, 1, '2024-2025', 'FINAL', 'OPEN', 'Семестр 1. На: Дмитрий Кузнецов. Экзамен: Веб-разработка', NOW()),
(8, 8, 2, '2024-2025', 'EXAM', 'CLOSED', 'Семестр 2. На: Анна Орлова. Экзамен: Алгоритмы', NOW() - INTERVAL '15 days'),
(9, 9, 1, '2024-2025', 'EXAM', 'OPEN', 'Семестр 1. На: Сергей Соколов. Экзамен: Операционные системы', NOW() - INTERVAL '3 days'),
(10, 10, 1, '2024-2025', 'ASSIGNMENT', 'OPEN', 'Семестр 1. На: Виктория Смирнова. Экзамен: Архитектура компьютеров', NOW() - INTERVAL '7 days');