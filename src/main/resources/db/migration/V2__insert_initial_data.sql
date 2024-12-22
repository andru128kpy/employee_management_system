-- Добавление тестовых данных в таблицу departments
INSERT INTO departments (name) VALUES
                                   ('HR'),
                                   ('IT'),
                                   ('Finance'),
                                   ('Marketing');

-- Добавление тестовых данных в таблицу employees
INSERT INTO employee (first_name, last_name, date_of_birth, manager, url_photo, status, email, deleted)
VALUES
    ('John', 'Doe', '1985-02-15', 'Jane Smith', 'http://example.com/photo1.jpg', 'Active', 'john.doe@example.com', FALSE),
    ('Alice', 'Johnson', '1990-06-10', 'Chris Evans', 'http://example.com/photo2.jpg', 'Active', 'alice.johnson@example.com', FALSE),
    ('Bob', 'Smith', '1978-11-23', NULL, 'http://example.com/photo3.jpg', 'Inactive', 'bob.smith@example.com', FALSE);

-- Добавление связей между employees и departments
INSERT INTO employee_department (employee_id, department_id) VALUES
                                                                 (1, 2), -- John Doe -> IT
                                                                 (2, 1), -- Alice Johnson -> HR
                                                                 (2, 2), -- Alice Johnson -> IT
                                                                 (3, 3); -- Bob Smith -> Finance