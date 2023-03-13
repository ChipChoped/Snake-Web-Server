DROP DATABASE IF EXISTS snake;
CREATE DATABASE IF NOT EXISTS snake DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE snake;

CREATE TABLE users (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(20),
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    email VARCHAR(50),
    password CHAR(64),
    sex ENUM('M', 'F'),
    victories INTEGER,
    birth_date DATE,
    inscription_date DATE,
    profile_picture LONGBLOB
);

CREATE TABLE games (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    user_id INTEGER,
    won BOOLEAN,
    score INTEGER,
    date DATETIME,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO users (username, first_name, last_name, email, password, sex, victories, birth_date, inscription_date) VALUES
('TheUnknownMan', 'John', 'Doe', 'john-doe@outlook.fr', SHA2('1234azerty', 256), 'M', 7, '1998-04-26', '2023-02-14');

INSERT INTO games (user_id, won, score, date) VALUES
(1, true, 50, '2023-02-24 16:23:52'),
(1, false, 0, '2023-02-24 17:16:22'),
(1, true, 72, '2023-02-25 15:54:08');