-- drop database if exists shop;

-- create database if not exists shop;
use shop;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTs `role`;

-- roles dictionary
CREATE TABLE `role` (
                        id INT NOT NULL AUTO_INCREMENT,
                        name varchar(20) NOT NULL,
                        PRIMARY KEY (id)
) ENGINE=InnoDB;




CREATE TABLE `user` (
                        id int NOT NULL AUTO_INCREMENT,
                        email varchar(320) unique,
						phone_number varchar(13) unique,
                        password varchar(160) NOT NULL, -- hex string representation of 512 bits PBKDF2 hash + 128 bits of salt (length=128+32)
                        
                        name varchar(255),
                        create_data TIMESTAMP NOT NULL DEFAULT current_timestamp,
                        role_id int,
                        locale varchar(2) default 'en', -- two-letter code of locale (ISO 639-1)
                        blocked bit NOT NULL default 0,           -- user is blocked if <> 0
                        PRIMARY KEY (id),
                        FOREIGN KEY (role_id) REFERENCES role(id) ON UPDATE CASCADE ON DELETE SET NULL
) ENGINE=InnoDB;


DROP TABLE IF EXISTS category;
CREATE TABLE category (
                        id int NOT NULL AUTO_INCREMENT,
                        name varchar(20) NOT NULL,
                        PRIMARY KEY (id)
) ENGINE=InnoDB;



DROP TABLE IF EXISTS `order_content`;
DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS product;
CREATE TABLE product (
                           id int NOT NULL AUTO_INCREMENT,
                           name varchar(255) NOT NULL,
                           brand varchar(255),
                           description TEXT,
                           price int NOT NULL,
                           image_url VARCHAR(2083) default 'https://tir-izmailovo.ru/wp-content/uploads/2016/10/404_error.jpg',
                           country varchar(255) default 'Ukraine',
                           category_id int,
                           
                           create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           PRIMARY KEY (id),
                           FOREIGN KEY (category_id) REFERENCES category(id) ON UPDATE CASCADE ON DELETE SET NULL
) ENGINE=InnoDB;





-- DROP TABLE IF EXISTS `parameter`;
-- DROP TABLE IF EXISTS `product_parameter`;


CREATE TABLE `order` (
                         id int NOT NULL AUTO_INCREMENT,
                         user_id int NOT NULL,
                         create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         status_id int,
                         PRIMARY KEY (id)
                         -- FOREIGN KEY (user_id) REFERENCES user(id) ON UPDATE CASCADE ON DELETE CASCADE
                         -- FOREIGN KEY (status_id) REFERENCES status(id) ON UPDATE CASCADE ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `order_content` (
                                 order_id int NOT NULL,
                                 product_id int NOT NULL,
                                 quantity int NOT NULL DEFAULT 1,
                                 FOREIGN KEY (order_id) REFERENCES `order`(id) ON UPDATE CASCADE ON DELETE CASCADE,
                                 FOREIGN KEY (product_id) REFERENCES product(id) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

commit;

-- DROP TABLE IF EXISTS `order_content`;