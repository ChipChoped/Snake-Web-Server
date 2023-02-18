DROP DATABASE IF EXISTS snake;
CREATE DATABASE IF NOT EXISTS snake DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE snake;

CREATE TABLE users (
    id INTEGER AUTO_INCREMENT PRIMARY KEY ,
    username VARCHAR(20),
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    email VARCHAR(50),
    password VARCHAR(20),
    sex ENUM('M', 'F'),
    victories INTEGER,
    birth_date DATE,
    inscription_date DATE
);

INSERT INTO users (username, first_name, last_name, email, password, sex, victories, birth_date, inscription_date) VALUES
('TheUnknownMan', 'John', 'Doe', 'john-doe@outlook.fr',
    '1234azerty', 'M', 7, '1998-04-26', now())