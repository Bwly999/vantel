CREATE DATABASE IF NOT EXISTS vantel DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_0900_ai_ci;


CREATE USER IF NOT EXISTS 'dbuser'@'localhost' IDENTIFIED BY '12345678';
CREATE USER IF NOT EXISTS 'dbuser'@'%' IDENTIFIED BY '12345678';

GRANT ALL ON vantel.* TO 'dbuser'@'localhost';
GRANT ALL ON vantel.* TO 'dbuser'@'%';
FLUSH PRIVILEGES;