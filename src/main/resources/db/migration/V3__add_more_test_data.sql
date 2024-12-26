INSERT INTO managers (name, email) VALUES
                                       ('John Doe', 'john.doe.manager@example.com'),
                                       ('Alice Johnson', 'alice.johnson.manager@example.com');

INSERT INTO departments (name) VALUES
                                   ('Sales'),
                                   ('Support');

INSERT INTO employee (first_name, last_name, date_of_birth, manager_id, photo_path, status, email, deleted)
VALUES
    ('Eva', 'Brown', '1992-03-28', 3, 'photo4.jpg', 'Active', 'eva.brown@example.com', FALSE),
    ('David', 'Wilson', '1980-07-05', 4, 'photo5.jpg', 'Active', 'david.wilson@example.com', FALSE),
    ('Carol', 'Davis', '1995-12-12', NULL, 'photo6.jpg', 'Inactive', 'carol.davis@example.com', FALSE);


INSERT INTO employee_department (employee_id, department_id) VALUES
                                                                 (4, 4), -- Eva Brown -> Sales
                                                                 (5, 5), -- David Wilson -> Support
                                                                 (6, 1); -- Carol Davis -> HR