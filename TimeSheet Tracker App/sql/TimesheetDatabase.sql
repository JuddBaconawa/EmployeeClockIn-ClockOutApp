CREATE DATABASE IF NOT EXISTS timesheetappdatabase;
USE timesheetappdatabase;

-- Drop old tables to start fresh
DROP TABLE IF EXISTS payroll;
DROP TABLE IF EXISTS timesheets;
DROP TABLE IF EXISTS salaries;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS users;

-- ================= USERS ==================
CREATE TABLE users (
    user_id INT(7) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(200) NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    role VARCHAR(200) NOT NULL DEFAULT 'employee',
    email VARCHAR(200) NOT NULL UNIQUE,
    phone VARCHAR(200),
    address VARCHAR(200),
    password VARCHAR(200) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insert 3 main users (admin, judd, chris)
INSERT INTO users (username, email, phone, address, password, role)
VALUES
('admin', 'admin@gmail.com', '+1234567890', 'New York, USA', 'password01', 'admin'),
('judd', 'juddbaconawa@gmail.com', '+15613171572', 'Florida, USA', 'password02', 'admin'),
('chris', 'chrisdoe@gmail.com', '+15613171574', 'Texas, USA', 'password03', 'employee');

-- ================= SALARIES ==================
CREATE TABLE salaries (
    salary_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    payType ENUM('hourly', 'salary') NOT NULL DEFAULT 'hourly',
    payRate DECIMAL(10,2) NOT NULL,
    effectiveDate DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO salaries (user_id, payType, payRate, effectiveDate)
VALUES
(1, 'salary', 95000.00, '2025-01-01'),
(2, 'salary', 88000.00, '2025-01-01'),
(3, 'hourly', 32.00, '2025-01-01');

-- ================= PROJECTS ==================
CREATE TABLE projects (
    project_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    name VARCHAR(200) NOT NULL,
    max_hours INT NOT NULL DEFAULT 40,
    hours_logged INT NOT NULL DEFAULT 0,
    start_date DATE NOT NULL,
    end_date DATE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO projects (user_id, name, max_hours, hours_logged, start_date, end_date)
VALUES
(2, 'Alpha', 40, 28, '2025-01-03', '2025-01-14'),
(2, 'Beta', 50, 35, '2025-03-10', '2025-03-20'),
(3, 'Gamma', 45, 32, '2025-05-05', '2025-05-15'),
(3, 'Delta', 60, 50, '2025-07-10', '2025-07-25'),
(2, 'Epsilon', 30, 25, '2025-09-01', '2025-09-07'),
(3, 'Zeta', 55, 44, '2025-11-05', '2025-11-18');

-- ================= TIMESHEETS ==================
CREATE TABLE timesheets (
    timesheet_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    project_id INT NOT NULL,
    clock_in DATETIME NOT NULL,
    clock_out DATETIME NOT NULL,
    break_minutes INT NOT NULL DEFAULT 30,
    work_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES projects(project_id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insert timesheet entries with break_minutes
-- ===== USER 2 (Judd) =====
INSERT INTO timesheets (user_id, project_id, clock_in, clock_out, break_minutes, work_date)
VALUES
(2, 1, '2025-01-06 09:00:00', '2025-01-06 17:00:00', 30, '2025-01-06'),
(2, 1, '2025-01-07 09:00:00', '2025-01-07 17:30:00', 30, '2025-01-07'),
(2, 1, '2025-01-08 09:15:00', '2025-01-08 17:15:00', 30, '2025-01-08'),
(2, 1, '2025-01-09 09:00:00', '2025-01-09 16:30:00', 30, '2025-01-09'),
(2, 1, '2025-01-10 08:45:00', '2025-01-10 17:00:00', 30, '2025-01-10'),
(2, 2, '2025-03-13 09:00:00', '2025-03-13 17:00:00', 30, '2025-03-13'),
(2, 2, '2025-03-14 09:00:00', '2025-03-14 17:30:00', 30, '2025-03-14'),
(2, 2, '2025-03-15 09:00:00', '2025-03-15 18:00:00', 30, '2025-03-15'),
(2, 2, '2025-03-16 08:30:00', '2025-03-16 16:30:00', 30, '2025-03-16'),
(2, 2, '2025-03-17 09:15:00', '2025-03-17 17:15:00', 30, '2025-03-17'),
(2, 5, '2025-09-04 09:00:00', '2025-09-04 17:00:00', 30, '2025-09-04'),
(2, 5, '2025-09-05 09:00:00', '2025-09-05 17:30:00', 30, '2025-09-05'),
(2, 5, '2025-09-06 08:45:00', '2025-09-06 16:45:00', 30, '2025-09-06'),
(2, 5, '2025-09-07 09:00:00', '2025-09-07 17:15:00', 30, '2025-09-07'),
(2, 5, '2025-09-08 09:30:00', '2025-09-08 17:30:00', 30, '2025-09-08');

-- ===== USER 3 (Chris) =====
INSERT INTO timesheets (user_id, project_id, clock_in, clock_out, break_minutes, work_date)
VALUES
(3, 3, '2025-05-08 09:00:00', '2025-05-08 17:00:00', 30, '2025-05-08'),
(3, 3, '2025-05-09 09:00:00', '2025-05-09 17:30:00', 30, '2025-05-09'),
(3, 3, '2025-05-10 08:45:00', '2025-05-10 17:00:00', 30, '2025-05-10'),
(3, 3, '2025-05-11 09:00:00', '2025-05-11 17:00:00', 30, '2025-05-11'),
(3, 3, '2025-05-12 09:15:00', '2025-05-12 17:15:00', 30, '2025-05-12'),
(3, 4, '2025-07-13 09:00:00', '2025-07-13 17:30:00', 30, '2025-07-13'),
(3, 4, '2025-07-14 09:00:00', '2025-07-14 17:00:00', 30, '2025-07-14'),
(3, 4, '2025-07-15 09:00:00', '2025-07-15 18:00:00', 30, '2025-07-15'),
(3, 4, '2025-07-16 08:30:00', '2025-07-16 16:30:00', 30, '2025-07-16'),
(3, 4, '2025-07-17 09:15:00', '2025-07-17 17:15:00', 30, '2025-07-17'),
(3, 6, '2025-11-08 09:00:00', '2025-11-08 17:00:00', 30, '2025-11-08'),
(3, 6, '2025-11-09 09:00:00', '2025-11-09 17:30:00', 30, '2025-11-09'),
(3, 6, '2025-11-10 08:45:00', '2025-11-10 17:00:00', 30, '2025-11-10'),
(3, 6, '2025-11-11 09:00:00', '2025-11-11 17:00:00', 30, '2025-11-11'),
(3, 6, '2025-11-12 09:15:00', '2025-11-12 17:15:00', 30, '2025-11-12');

-- ================= PAYROLL ==================
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