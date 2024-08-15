-- Mysql Queries to create tables make sure to use correct database

-- Create user table
CREATE TABLE IF NOT EXISTS users (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  type ENUM('owner', 'user') NOT NULL,
  name varchar(255) NOT NULL,
  mobile varchar(255) NOT NULL,
  email varchar(255) NOT NULL UNIQUE,
  password varchar(255) NOT NULL
);

-- create hotel table
CREATE TABLE IF NOT EXISTS hotels (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) NOT NULL,
  city varchar(255) NOT NULL,
  owner_id BIGINT NOT NULL,
  FOREIGN KEY (owner_id) REFERENCES users(id)
);

-- create food table
CREATE TABLE IF NOT EXISTS foods (
  id BIGINT Not NULL AUTO_INCREMENT PRIMARY KEY,
  hotel_id BIGINT NOT NULL,
  name varchar(255) NOT NULL,
  price int NOT NULL,
  FOREIGN KEY (hotel_id) REFERENCES hotels(id)
);

-- create order table
CREATE TABLE IF NOT EXISTS orders (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  food_id BIGINT NOT NULL,
  quantity int NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (food_id) REFERENCES foods(id)
);

-- create cart table
CREATE TABLE IF NOT EXISTS carts (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  food_id BIGINT NOT NULL,
  quantity int NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (food_id) REFERENCES foods(id)
);

-- create rating table
CREATE TABLE IF NOT EXISTS ratings (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  food_id BIGINT NOT NULL,
  rating int NOT NULL,
  message varchar(255) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (food_id) REFERENCES foods(id)
);
