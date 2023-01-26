DROP DATABASE IF EXISTS snake;
CREATE DATABASE IF NOT EXISTS snake;
USE snake;

CREATE TABLE users (
    username VARCHAR(20),
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    email VARCHAR(50),
    password VARCHAR(20),
    sex ENUM('M', 'F'),
    birth_date DATE,
    inscription_date DATE
);

INSERT INTO users VALUES
('SuperSnake', 'John', 'Smith', 'john-smith@outlook.fr',
    '1234azerty', 'M', '1998-04-26', now())