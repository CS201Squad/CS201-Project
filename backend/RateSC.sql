DROP DATABASE IF EXISTS RateSC;
CREATE DATABASE RateSC;

USE RateSC;
CREATE TABLE Professor (
	professorID Float(14) PRIMARY KEY NOT NULL,
    fname VARCHAR(50) NOT NULL,
    lname VARCHAR(50) NOT NULL,
    email VARCHAR(70),
    password_hash VARCHAR(45)
);

INSERT INTO Professor(professorID, fname, lname)
VALUES(1408305443, 'Jeffery', 'Miller'),
	(4238483942, 'Tampa', 'Jenn'),
	(8390492739, 'Ben', 'Plattner'),
	(3427894738, 'Shauna', 'Finkelbottom'),
	(4324789243, 'Sarah', 'Tomita'),
	(7463829468, 'Laurance', 'Fetty'),
	(4738294723, 'Lena', 'Louse');

CREATE TABLE Student (
	studentID FLOAT(15) PRIMARY KEY NOT NULL, 
    email VARCHAR(70) NOT NULL,
    password_hash VARCHAR(45) NOT NULL
);

INSERT INTO Student(studentID, email, password_hash) VALUES
(1290390128, 'tonytrojan@usc.edu', 'abc'),
(1234567890, 'tommytrojan@usc.edu', 'abc'),
(0987654321, 'tinatrojan@usc.edu', 'abc'),
(1029831090, 'tammytrojan@usc.edu', 'abc');

CREATE TABLE Grades (
	gradeID INT(11) PRIMARY KEY NOT NULL,
    Aplus INT(11),
    A INT(11),
    Aminus INT(11),
    Bplus INT(11),
    B INT(11),
    Bminus INT(11),
    Cplus INT(11),
    C INT(11),
    Cminus INT(11),
    Dplus INT(11),
    D INT(11),
    Dminus INT(11),
    F INT(11),
    pass INT(11),
    npass INT(11)
);

INSERT INTO Grades(gradeID, Aplus, A, Aminus, Bplus, B, Bminus, Cplus, C, Cminus, Dplus, D, 
Dminus, F, pass, npass)
Values (123, 3, 6, 20, 20, 5, 5, 5, 0, 0, 0, 3, 0, 2, 6, 2),
(122, 2, 5, 60, 50, 48, 30, 15, 3, 0, 0, 3, 0, 0, 18, 4),
(142, 5, 5, 6, 5, 18, 20, 15, 3, 0, 1, 3, 0, 0, 1, 4),
(165, 10, 14, 27, 16, 4, 4, 2, 1, 0, 0, 0, 0, 0, 5, 2),
(172, 10, 14, 27, 16, 4, 4, 2, 1, 0, 0, 0, 0, 0, 5, 2),
(109, 5, 5, 6, 5, 18, 20, 15, 3, 0, 1, 3, 0, 0, 1, 4),
(178, 2, 5, 60, 50, 48, 30, 15, 3, 0, 0, 3, 0, 0, 18, 4),
(712, 3, 6, 20, 20, 5, 5, 5, 0, 0, 0, 3, 0, 2, 6, 2),
(195, 3, 6, 20, 20, 5, 5, 5, 0, 0, 0, 3, 0, 2, 6, 2),
(239, 5, 5, 6, 5, 18, 20, 15, 3, 0, 1, 3, 0, 0, 1, 4),
(543, 10, 14, 27, 16, 4, 4, 2, 1, 0, 0, 0, 0, 0, 5, 2),
(999, 2, 5, 60, 50, 48, 30, 15, 3, 0, 0, 3, 0, 0, 18, 4);


CREATE TABLE Course(
	courseID FLOAT(11) PRIMARY KEY NOT NULL,
    name VARCHAR(50),
    gradeID INT(15),
    prefix VARCHAR(5) NOT NULL, 
    courseNum INT(5) NOT NULL,
    term VARCHAR(11) NOT NULL,
    professorID Float(14) NOT NULL,
    overall FLOAT(11, 5),
    difficulty FLOAT(11, 5),
    workload FLOAT(11,5),
    clarity FLOAT(11,5),
    FOREIGN KEY fk2(gradeID) REFERENCES Grades(gradeID),
    FOREIGN KEY fk3(professorID) REFERENCES Professor(professorID)
);

INSERT INTO Course(courseID, name, gradeID, prefix, courseNum, term, professorID, 
overall, difficulty, workload, clarity)
Values (4324, 'Principles of Software Development', 123, 'CSCI', 201, 'Fall 2020', 1408305443, 
4.6, 3.0, 3.4, 4.0),
(6382, 'Principles of Software Development', 122, 'CSCI', 201, 'Spring 2019', 1408305443, 
3.6, 2.0, 2.4, 3.0),
(8432, 'Principles of Software Development', 165, 'CSCI', 201, 'Spring 2019', 4238483942, 
2.6, 4.0, 2.4, 3.0),
(8274, 'Intro to Phython', 142, 'ITP', 115, 'Spring 2019', 1408305443, 
4.6, 1.0, 2.1, 3.5),
(4310, 'Digging into the Past', 172, 'AHIS', 201, 'Fall 2020', 8390492739, 2.3, 1.2, 5.0, 2.1),
(1234, 'Introduction to Programming', 109, 'CSCI', 103, 'Spring 2019', 3427894738, 3.0, 2.5, 4.3, 3.3),
(1410, 'Introduction to Programming', 178, 'CSCI', 103, 'Fall 2019', 4324789243, 4.1, 3.2, 3.1, 4.5),
(1459, 'Introduction to Programming', 712, 'CSCI', 103, 'Fall 2019', 3427894738, 3.0, 2.5, 4.3, 3.3),
(3212, 'Data Structures and Object Oriented Design', 195, 'CSCI', 104, 'Fall 2020', 4324789243, 4.1, 3.2, 3.1, 4.5),
(7829, 'Data Structures and Object Oriented Design', 239, 'CSCI', 104, 'Spring 2019', 3427894738, 3.0, 2.5, 4.3, 3.3),
(9103, 'Software Engineering', 543, 'CSCI', 310, 'Fall 2020', 4738294723, 3.4, 3.4, 5.0, 2.1),
(1293, 'Software Engineering', 999, 'CSCI', 310, 'Spring 2019', 7463829468, 1.4, 1.4, 5.0, 1.1);


CREATE TABLE Review(
	reviewID INT(11) PRIMARY KEY NOT NULL,
    courseID FLOAT(11) NOT NULL,
    content VARCHAR(500),
    studentID FLOAT(15) NOT NULL,
    create_at TIMESTAMP NOT NULL,
    overall FLOAT(11, 5),
    difficulty FLOAT(11, 5),
    workload FLOAT(11,5),
    clarity FLOAT(11,5),
    textbook boolean,
    attendance boolean,
    FOREIGN KEY fk4(studentID) REFERENCES Student(studentID),
    FOREIGN KEY fk7(courseID) REFERENCES Course(courseID)
);

INSERT INTO Review(reviewID, courseID, content, studentID, create_at, 
	overall, difficulty, workload, clarity, textbook, attendance) VALUES 
	(12, 4324, 'Really enjoyed the class!', 1290390128, '2020-04-20 00:00:01', 4.1, 3.2, 4.3, 4.5, 1, 1),
	(23, 4324, 'I enjoyed how the professor teaches!', 1234567890, '2020-04-20 00:00:02', 4.3, 4.0, 4.0, 4.5, 1, 1),
	(34, 6382, 'I love this class!', 0987654321, '2020-04-20 00:00:01', 4.5, 3.6, 5, 4.1, 0, 1),
	(45, 8432, 'Professor was great! Would take again!', 1029831090, '2020-04-20 00:00:02', 4.0, 4.5, 4.3, 4.5, 0, 1),
	(56, 8274, 'Really enjoyed the class!', 1290390128, '2020-04-20 00:00:01', 4.1, 3.2, 4.3, 4.5, 1, 1),
	(67, 4310, 'I enjoyed how the professor teaches!', 1290390128, '2020-04-20 00:00:02', 4.1, 3.2, 4.3, 4.5, 1, 1),
	(78, 1234, 'I love this class!', 0987654321, '2020-04-20 00:00:01', 4.5, 3.6, 5, 4.1, 0, 1),
	(89, 1234, 'Professor was great! Would take again!', 1029831090, '2020-04-20 00:00:02', 4.0, 4.5, 4.3, 4.5, 0, 1),
	(98, 1410, 'Really enjoyed the class!', 1290390128, '2020-04-20 00:00:01', 4.1, 3.2, 4.3, 4.5, 1, 1),
	(87, 1459, 'I enjoyed how the professor teaches!', 1234567890, '2020-04-20 00:00:02', 4.3, 4.0, 4.0, 4.5, 1, 1),
	(76, 1459, 'I love this class!', 0987654321, '2020-04-20 00:00:01', 4.5, 3.6, 5, 4.1, 0, 1),
	(65, 3212, 'Professor was great! Would take again!', 1029831090, '2020-04-20 00:00:02', 4.0, 4.5, 4.3, 4.5, 0, 1),
	(54, 3212, 'Really enjoyed the class!', 1290390128, '2020-04-20 00:00:01', 4.1, 3.2, 4.3, 4.5, 1, 1),
	(43, 7829, 'I enjoyed how the professor teaches!', 1234567890, '2020-04-20 00:00:02', 4.3, 4.0, 4.0, 4.5, 1, 1),
	(32, 7829, 'I love this class!', 0987654321, '2020-04-20 00:00:01', 4.5, 3.6, 5, 4.1, 0, 1),
	(21, 9103, 'Professor was great! Would take again!', 1029831090, '2020-04-20 00:00:02', 4.0, 4.5, 4.3, 4.5, 0, 1),
	(10, 9103, 'Really enjoyed the class!', 1290390128, '2020-04-20 00:00:01', 4.1, 3.2, 4.3, 4.5, 1, 1),
	(11, 1293, 'I enjoyed how the professor teaches!', 1234567890, '2020-04-20 00:00:02', 4.3, 4.0, 4.0, 4.5, 1, 1),
	(22, 1293, 'I love this class!', 0987654321, '2020-04-20 00:00:01', 4.5, 3.6, 5, 4.1, 0, 1),
	(33, 1293, 'Professor was great! Would take again!', 1029831090, '2020-04-20 00:00:02', 4.0, 4.5, 4.3, 4.5, 0, 1);


CREATE TABLE Taken(
	professorID Float(14) NOT NULL,
	studentID FLOAT(15) NOT NULL,
    prefix VARCHAR(5) NOT NULL, 
    courseNum INT(5) NOT NULL,
	FOREIGN KEY fk6(professorID) REFERENCES Professor(professorID),
    FOREIGN KEY fk5(studentID) REFERENCES Student(studentID)
);