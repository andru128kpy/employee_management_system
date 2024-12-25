-- Добавление новых данных в таблицу managers
INSERT INTO managers (name, email) VALUES
                                       ('John Doe', 'john.doe.manager@example.com'),
                                       ('Alice Johnson', 'alice.johnson.manager@example.com');

-- Добавление новых данных в таблицу departments
INSERT INTO departments (name) VALUES
                                   ('Sales'),
                                   ('Support');

-- Добавление новых данных в таблицу employees
INSERT INTO employee (first_name, last_name, date_of_birth, manager_id, url_photo, status, email, deleted)
VALUES
    ('Eva', 'Brown', '1992-03-28', 3, 'http://example.com/photo4.jpg', 'Active', 'eva.brown@example.com', FALSE),
    ('David', 'Wilson', '1980-07-05', 4, 'http://example.com/photo5.jpg', 'Active', 'david.wilson@example.com', FALSE),
    ('Carol', 'Davis', '1995-12-12', NULL, 'http://example.com/photo6.jpg', 'Inactive', 'carol.davis@example.com', FALSE);

-- Добавление связей между новыми сотрудниками и отделами
INSERT INTO employee_department (employee_id, department_id) VALUES
                                                                 (4, 4), -- Eva Brown -> Sales
                                                                 (5, 5), -- David Wilson -> Support
                                                                 (6, 1); -- Carol Davis -> HR