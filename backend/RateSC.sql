DROP DATABASE IF EXISTS RateSC;
CREATE DATABASE RateSC;

USE RateSC;
CREATE TABLE Professor (
	professorID Float(14) PRIMARY KEY NOT NULL,
    fname VARCHAR(50) NOT NULL,
    lname VARCHAR(50) NOT NULL,
    email VARCHAR(70),
    password_hash VARCHAR(45),
    department VARCHAR(20)
);

INSERT INTO Professor(professorID, fname, lname, department)
VALUES(14, 'Jeffery', 'Miller', 'ITP'),
	(42, 'Tampa', 'Jenn', 'CSCI'),
	(83, 'Ben', 'Plattner', 'CSCI'),
	(34, 'Shauna', 'Finkelbottom', 'CSCI'),
	(432, 'Sarah', 'Tomita', 'CSCI'),
	(7463, 'Laurance', 'Fetty', 'CSCI'),
	(4738, 'Lena', 'Louse', 'CSCI');

CREATE TABLE Student (
	studentID FLOAT(15) PRIMARY KEY NOT NULL, 
    email VARCHAR(70) NOT NULL,
    password_hash VARCHAR(45) NOT NULL
);

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
(165, 10, 14, 27, 16, 4, 4, 2, 1, 0, 0, 0, 0, 0, 5, 2);


CREATE TABLE Course(
	courseID FLOAT(11) PRIMARY KEY NOT NULL,
    title VARCHAR(50),
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

INSERT INTO Course(courseID, title, gradeID, prefix, courseNum, term, professorID, 
overall, difficulty, workload, clarity)
Values (4324, 'Software Engineering', 123, 'CSCI', 201, 'FALL 2020', 14, 
4.6, 3.0, 3.4, 4.0),
(6382, 'Software Engineering', 122, 'CSCI', 201, 'Spring 2019', 14, 
3.6, 2.0, 2.4, 3.0),
(8432, 'Software Engineering', 165, 'CSCI', 201, 'Spring 2019', 42, 
2.6, 4.0, 2.4, 3.0),
(8274, 'Intro to Phython', 142, 'ITP', 115, 'Spring 2019', 14, 
4.6, 1.0, 2.1, 3.5);


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

CREATE TABLE Taken(
	professorID Float(14) NOT NULL,
	studentID FLOAT(15) NOT NULL,
    prefix VARCHAR(5) NOT NULL, 
    courseNum INT(5) NOT NULL,
	FOREIGN KEY fk6(professorID) REFERENCES Professor(professorID),
    FOREIGN KEY fk5(studentID) REFERENCES Student(studentID)
)

