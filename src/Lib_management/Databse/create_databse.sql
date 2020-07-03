-- ADD A COUMN IN THE ISSUED BOOKS TBALE WITH C_NAME STAUS WITH 2 VALUES AS "RETURNED" OR "NOT  RETURNED"--  
CREATE DATABASE IF NOT EXISTS lib_management;
USE lib_management;
CREATE TABLE IF NOT EXISTS students(
	usn VARCHAR(12) PRIMARY KEY,
    name VARCHAR(50),
    branch VARCHAR(10),
    year VARCHAR(10),
    sem VARCHAR(5),
    e_mail VARCHAR(100),
    password VARCHAR(50) UNIQUE KEY);

CREATE TABLE IF NOT EXISTS books(
	book_id VARCHAR(10)  PRIMARY KEY,
    Name VARCHAR(100),
    `author's_name` VARCHAR(50),
    publisher VARCHAR(50) DEFAULT NULL,
    quantity INT,
    stock INT );

CREATE TABLE IF NOT EXISTS issued_books(
	usn VARCHAR(12) ,
    book_id VARCHAR(10) ,
    date_of_issue DATE NOT NULL ,
    return_date DATE NOT NULL,
    fine INT DEFAULT 0, 
    PRIMARY KEY (usn,book_id),
    FOREIGN KEY(usn) REFERENCES students(usn) ON UPDATE CASCADE,
    FOREIGN KEY(book_id) REFERENCES books(book_id) ON UPDATE CASCADE);
    
CREATE TABLE IF NOT EXISTS admin
	(username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(50) NOT NULL);

