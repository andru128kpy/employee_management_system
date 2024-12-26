
INSERT INTO managers (name, email) VALUES
                                       ('Jane Smith', 'jane.smith@example.com'),
                                       ('Chris Evans', 'chris.evans@example.com');


INSERT INTO departments (name) VALUES
                                   ('HR'),
                                   ('IT'),
                                   ('Finance'),
                                   ('Marketing');

-- Добавление тестовых данных в таблицу employees
INSERT INTO employee (first_name, last_name, date_of_birth, manager_id, photo_path, status, email, deleted)
VALUES
    ('John', 'Doe', '1985-02-15', 1, 'photo1.jpg', 'Active', 'john.doe@example.com', FALSE),
    ('Alice', 'Johnson', '1990-06-10', 2, 'photo2.jpg', 'Active', 'alice.johnson@example.com', FALSE),
    ('Bob', 'Smith', '1978-11-23', NULL, 'photo3.jpg', 'Inactive', 'bob.smith@example.com', FALSE);

INSERT INTO employee_department (employee_id, department_id) VALUES
                                                                 (1, 2), -- John Doe -> IT
                                                                 (2, 1), -- Alice Johnson -> HR
                                                                 (2, 2), -- Alice Johnson -> IT
                                                                 (3, 3); -- Bob Smith -> Finance