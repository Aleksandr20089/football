-- 1. Добавляем тренеров
INSERT INTO coach (name, experience_years) VALUES ('Пеп Гвардиола', 15);
INSERT INTO coach (name, experience_years) VALUES ('Карло Анчелотти', 25);

-- 2. Добавляем владельцев
INSERT INTO owner (name, budget) VALUES ('Александр Забродний', 1000000000.0);
INSERT INTO owner (name, budget) VALUES ('Флорентино Перес', 500000000.0);

-- 3. Добавляем клубы (связываем с тренерами и владельцами по ID)
INSERT INTO club (name, coach_id, owner_id) VALUES ('Манчестер Сити', 1, 1);
INSERT INTO club (name, coach_id, owner_id) VALUES ('Реал Мадрид', 2, 2);

-- 4. Добавляем игроков
INSERT INTO player (name, number, club_id) VALUES ('Ламин Ямаль', 9, 1);
INSERT INTO player (name, number, club_id) VALUES ('Килиян Мбаппе', 7, 2);