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

-- ================= TIMESHEETS ==================
CREATE TABLE timesheets (
    timesheet_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    project_id INT NOT NULL,
    clock_in DATETIME,
    clock_out DATETIME,
    total_hours DECIMAL(5,2),
    work_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES projects(project_id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO timesheets (user_id, project_id, clock_in, clock_out, total_hours, work_date)
VALUES ()

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

-- ================= PROJECT DATA ==================
INSERT INTO projects (user_id, name, max_hours, hours_logged, start_date, end_date)
VALUES
(2, 'Alpha', 40, 28, '2025-01-03', '2025-01-14'),
(2, 'Beta', 50, 35, '2025-03-10', '2025-03-20'),
(3, 'Gamma', 45, 32, '2025-05-05', '2025-05-15'),
(3, 'Delta', 60, 50, '2025-07-10', '2025-07-25'),
(2, 'Epsilon', 30, 25, '2025-09-01', '2025-09-07'),
(3, 'Zeta', 55, 44, '2025-11-05', '2025-11-18');

-- ================= TIMESHEET ENTRIES ==================
-- Judd’s projects (Alpha, Beta, Epsilon)
INSERT INTO timesheets (user_id, project_id, clock_in, clock_out, total_hours, work_date)
VALUES
-- Alpha
(2, 1, '2025-01-03 09:00:00', '2025-01-03 17:00:00', 8, '2025-01-03'),
(2, 1, '2025-01-04 09:00:00', '2025-01-04 17:00:00', 8, '2025-01-04'),
(2, 1, '2025-01-05 09:00:00', '2025-01-05 16:00:00', 7, '2025-01-05'),

-- Beta
(2, 2, '2025-03-10 09:00:00', '2025-03-10 17:00:00', 8, '2025-03-10'),
(2, 2, '2025-03-11 09:00:00', '2025-03-11 17:30:00', 8.5, '2025-03-11'),
(2, 2, '2025-03-12 09:00:00', '2025-03-12 17:00:00', 8, '2025-03-12'),

-- Epsilon
(2, 5, '2025-09-01 09:00:00', '2025-09-01 17:00:00', 8, '2025-09-01'),
(2, 5, '2025-09-02 09:00:00', '2025-09-02 17:15:00', 8.25, '2025-09-02'),
(2, 5, '2025-09-03 09:00:00', '2025-09-03 16:45:00', 7.75, '2025-09-03');

-- Chris’s projects (Gamma, Delta, Zeta)
INSERT INTO timesheets (user_id, project_id, clock_in, clock_out, total_hours, work_date)
VALUES
-- Gamma
(3, 3, '2025-05-05 08:45:00', '2025-05-05 17:00:00', 8.25, '2025-05-05'),
(3, 3, '2025-05-06 09:00:00', '2025-05-06 17:00:00', 8, '2025-05-06'),
(3, 3, '2025-05-07 09:00:00', '2025-05-07 17:15:00', 8.25, '2025-05-07'),

-- Delta
(3, 4, '2025-07-10 09:00:00', '2025-07-10 17:30:00', 8.5, '2025-07-10'),
(3, 4, '2025-07-11 09:00:00', '2025-07-11 17:00:00', 8, '2025-07-11'),
(3, 4, '2025-07-12 09:00:00', '2025-07-12 17:00:00', 8, '2025-07-12'),

-- Zeta
(3, 6, '2025-11-05 09:00:00', '2025-11-05 17:00:00', 8, '2025-11-05'),
(3, 6, '2025-11-06 09:15:00', '2025-11-06 17:15:00', 8, '2025-11-06'),
(3, 6, '2025-11-07 09:00:00', '2025-11-07 17:30:00', 8.5, '2025-11-07');
