DROP DATABASE IF EXISTS snake;
CREATE DATABASE IF NOT EXISTS snake DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE snake;

CREATE TABLE users (
    username VARCHAR(20) PRIMARY KEY,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    email VARCHAR(50),
    password VARCHAR(20),
    sex ENUM('M', 'F'),
    birth_date DATE,
    inscription_date DATE
);

INSERT INTO users VALUES
('TheUnknownMan', 'John', 'Doe', 'john-doe@outlook.fr',
    '1234azerty', 'M', '1998-04-26', now())