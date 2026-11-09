-- SQL queries practiced:

--
mysql> show databases;

-- Create database
mysql>CREATE DATABASE IF NOT EXISTS company_db;

mysql> use company_db;

mysql> show tables;

-- Employer table (Parent)
mysql>CREATE TABLE Employer (
    employer_id     INT AUTO_INCREMENT PRIMARY KEY,
    company_name    VARCHAR(100) NOT NULL,
    industry        VARCHAR(50),
    city            VARCHAR(50),
    country         VARCHAR(50),
    established_date DATE,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Employee table (Child) — FK references Employer
mysql>CREATE TABLE Employee (
    employee_id     INT AUTO_INCREMENT PRIMARY KEY,
    first_name      VARCHAR(50) NOT NULL,
    last_name       VARCHAR(50) NOT NULL,
    email           VARCHAR(100) UNIQUE NOT NULL,
    phone           VARCHAR(15),
    designation     VARCHAR(50),
    salary          DECIMAL(10,2) DEFAULT 0.00,
    hire_date       DATE,
    employer_id     INT NOT NULL,
    manager_id      INT DEFAULT NULL,          -- self-referencing FK (reporting hierarchy)
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_employer
        FOREIGN KEY (employer_id) REFERENCES Employer(employer_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_manager
        FOREIGN KEY (manager_id) REFERENCES Employee(employee_id)
        ON DELETE SET NULL
);

mysql>INSERT INTO Employer (company_name, industry, city, country, established_date) VALUES
('TechNova Pvt Ltd', 'IT Services', 'Hyderabad', 'India', '2005-06-15');

-- Select with condition
mysql>SELECT first_name, last_name, salary FROM Employee WHERE salary > 1000;

-- Select with join (to Employer)
mysql>SELECT e.first_name, e.designation, emp.company_name
		FROM Employee e
		JOIN Employer emp ON e.employer_id = emp.employer_id;

--UPDATE sql
mysql>UPDATE Employee
		SET salary = salary * 1.10
		WHERE designation = 'Automation Engineer';
		
-- DELETE sql
mysql>DELETE FROM Employee
		WHERE employee_id = 5;

-- Soft-delete pattern (commonly preferred in real systems — good to mention in interviews)
mysql>ALTER TABLE Employee ADD COLUMN is_active BOOLEAN DEFAULT TRUE;
		UPDATE Employee SET is_active = FALSE WHERE employee_id = 5;

mysql>DELETE FROM users WHERE status = 'inactive';

mysql>TRUNCATE TABLE users;

mysql>DROP TABLE users;


mysql>SELECT e.first_name, e.designation, emp.company_name
		FROM Employee e
		JOIN Employer emp ON e.employer_id = emp.employer_id;


mysql> select * from city;

mysql> select * from city where CountryCode='ind';

-- write sql query to to find upicsate reords from Employeer table based on the city

mysql> SELECT * FROM Employer WHERE City = 'Mumbai' LIMIT 2;

mysql> SELECT * FROM Employer WHERE City IS NULL;

mysql> SELECT * FROM Employer WHERE City IS NOT NULL;

mysql> desc employee; -- displaye table STRUCTURE

mysql> select count(*) from city; -- get the total rows

mysql> select * from employee where salary>5000;


-- comment ------------------------------------------------------------------------------------------------------------------------------------------------------------



mysql> desc employee;
+-------------+---------------+------+-----+-------------------+-------------------+
| Field       | Type          | Null | Key | Default           | Extra             |
+-------------+---------------+------+-----+-------------------+-------------------+
| employee_id | int           | NO   | PRI | NULL              | auto_increment    |
| first_name  | varchar(50)   | NO   |     | NULL              |                   |
| last_name   | varchar(50)   | NO   |     | NULL              |                   |
| email       | varchar(100)  | NO   | UNI | NULL              |                   |
| phone       | varchar(15)   | YES  |     | NULL              |                   |
| designation | varchar(50)   | YES  |     | NULL              |                   |
| salary      | decimal(10,2) | YES  |     | 0.00              |                   |
| hire_date   | date          | YES  |     | NULL              |                   |
| employer_id | int           | NO   | MUL | NULL              |                   |
| manager_id  | int           | YES  | MUL | NULL              |                   |
| created_at  | timestamp     | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
| is_active   | tinyint(1)    | YES  |     | 1                 |                   |
+-------------+---------------+------+-----+-------------------+-------------------+
12 rows in set (0.062 sec)

mysql> desc employer;
+------------------+--------------+------+-----+-------------------+-------------------+
| Field            | Type         | Null | Key | Default           | Extra             |
+------------------+--------------+------+-----+-------------------+-------------------+
| employer_id      | int          | NO   | PRI | NULL              | auto_increment    |
| company_name     | varchar(100) | NO   |     | NULL              |                   |
| industry         | varchar(50)  | YES  |     | NULL              |                   |
| city             | varchar(50)  | YES  |     | NULL              |                   |
| country          | varchar(50)  | YES  |     | NULL              |                   |
| established_date | date         | YES  |     | NULL              |                   |
| created_at       | timestamp    | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
+------------------+--------------+------+-----+-------------------+-------------------+
7 rows in set (0.052 sec)



-- JOIN -- INNER JOIN 

mysql> select e.first_name, er.company_name from Employee e INNER JOIN Employer er ON e.employer_id = er.EMPLOYER_id;
+------------+--------------+
| first_name | company_name |
+------------+--------------+
| Ravi       | ECS          |
| Anjali     | ECS          |
| Suresh     | LWT          |
| Priya      | LWT          |
| Karan      | Rupeelog     |
+------------+--------------+
5 rows in set (0.014 sec)

mysql> SELECT E.FIRST_NAME, ER.COMPANY_NAME FROM EMPLOYEE E INNER JOIN EMPLOYER ER ON E.EMPLOYER_ID = ER.EMPLOYER_ID;
+------------+--------------+
| FIRST_NAME | COMPANY_NAME |
+------------+--------------+
| Ravi       | ECS          |
| Anjali     | ECS          |
| Suresh     | LWT          |
| Priya      | LWT          |
| Karan      | Rupeelog     |
+------------+--------------+
5 rows in set (0.014 sec)







