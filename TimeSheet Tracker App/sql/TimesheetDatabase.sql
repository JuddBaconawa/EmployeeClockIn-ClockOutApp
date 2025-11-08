CREATE DATABASE IF NOT EXISTS timesheetappdatabase;
USE timesheetappdatabase;

-- DROP Tables for mystore to clear them.
DROP TABLE IF EXISTS payroll;
DROP TABLE IF EXISTS timesheets;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS salaries;



CREATE TABLE users (
	user_id INT(7) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(200) NOT NULL,
    role VARCHAR(200) NOT NULL DEFAULT 'employee',
    email VARCHAR(200) NOT NULL UNIQUE,
    phone VARCHAR(200),
    address VARCHAR(200),
    password VARCHAR(200) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);



CREATE TABLE salaries (
    salary_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    payType ENUM('hourly', 'salary') NOT NULL DEFAULT 'hourly',
    payRate DECIMAL(10,2) NOT NULL,
    effectiveDate DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) on DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE timesheets (
    timesheet_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    clock_in DATETIME,
    clock_out DATETIME,
    total_hours DECIMAL(5,2),
    work_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE payroll (
    payroll_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    period_start DATE NOT NULL,
    period_end DATE NOT NULL,
    total_hours DECIMAL(6,2),
    total_pay DECIMAL(10,2),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);





-- ADMIN
INSERT INTO users (username, email, phone, address, password, role)
    VALUES ('admin', 'admin@gmail.com', '+1234567890', 'New York, USA', 'password01', 'admin');

-- Employee
INSERT INTO users (username, email, phone, address, password, role)
    VALUES 
    ('dulce', 'dulcebaconawa@gmail.com', '+15613171571', 'Florida, USA', 'password01', 'employee'),
    ('judd', 'juddbaconawa@gmail.com', '+15613171572', 'Florida, USA', 'password02', 'admin'),        
    ('maria', 'mariacruz@gmail.com', '+15613171573', 'California, USA', 'password03', 'manager');