CREATE DATABASE IF NOT EXISTS lib_management;
USE lib_management;

CREATE TABLE IF NOT EXISTS books(id VARCHAR(8) PRIMARY KEY,name VARCHAR(20),author_name VARCHAR(25),quantity INT);

DROP TABLE IF EXISTS students;

CREATE TABLE students(
	usn VARCHAR(12) PRIMARY KEY,
    name VARCHAR(50),
    branch VARCHAR(10),
    year VARCHAR(10),
    sem VARCHAR(5),
    e_mail VARCHAR(100),
    password VARCHAR(15) UNIQUE KEY);

INSERT INTO students (usn,name,branch,year,sem,e_mail,password)VALUES 
	('1SI18EC088','Rahul','ECE','second','4th','rahhul@gmail.com','rahul'),
    ('1SI18EC081','Rishabh kumar','ECE','second','4th','rishabh@gmail.com','rishabh'),
    ('1SI18EC082','MS Dhoni','ECE','second','4th','MSDhoni@gmail.com','MSDhoni'),
    ('1SI18EC083','Sahil ','ECE','second','4th','Sahil@gmail.com','Sahil'),
    ('1SI18EC084','Barjesh','ECE','second','4th','Barjesh@gmail.com','Barjesh'),
    ('1SI18EC085','Suresh','ECE','second','4th','suresh@gmail.com','suresh'),
    ('1SI18EC060','Rakesh kumar','ECE','second','4th','rakesh@gmail.com','rakesh'),
    ('1SI18EC089','Tharun kumar','ECE','second','4th','tharun@gmail.com','tharun'),
    ('1SI18EC044','Raman kumar','ECE','second','4th','raman@gmail.com','raman'),
    ('1SI18EC099','saurabh kumar','ECE','second','4th','saurabh@gmail.com','saurbh'),
    ('1SI18EC022','Varun kumar','ECE','second','4th','varun@gmail.com','varun');

SELECT * FROM students;

DROP TABLE IF EXISTS books;

CREATE TABLE IF NOT EXISTS books(
	book_id INT PRIMARY KEY,
    Name VARCHAR(100),
    `author's_name` VARCHAR(50),
    publisher VARCHAR(50),
    quantity INT);

DESC books;

DROP TABLE IF EXISTS issued_books;	

CREATE TABLE IF NOT EXISTS issued_books(
	usn VARCHAR(12) ,
    book_id INT ,
    date_of_issue DATE NOT NULL,
    return_date DATE NOT NULL,
    fine INT, 
    PRIMARY KEY (usn,book_id),
    FOREIGN KEY(usn) REFERENCES students(usn) ON UPDATE CASCADE,
    FOREIGN KEY(book_id) REFERENCES books(book_id) ON UPDATE CASCADE);
    

CREATE TABLE IF NOT EXISTS admin
	(username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(50) NOT NULL);

INSERT INTO admin(username,password) VALUES('admin',"admin");
