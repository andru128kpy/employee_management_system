-- Создание таблицы managers
CREATE TABLE managers (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          email VARCHAR(255)
);

-- Создание таблицы departments
CREATE TABLE departments (
                             id BIGSERIAL PRIMARY KEY,
                             name VARCHAR(255) NOT NULL
);

-- Создание таблицы employees
CREATE TABLE employee (
                          id BIGSERIAL PRIMARY KEY,
                          first_name VARCHAR(100) NOT NULL,
                          last_name VARCHAR(100) NOT NULL,
                          date_of_birth DATE NOT NULL,
                          manager_id BIGINT, -- Внешний ключ на менеджера
                          url_photo VARCHAR(500),
                          status VARCHAR(100),
                          email VARCHAR(255) UNIQUE NOT NULL,
                          deleted BOOLEAN DEFAULT FALSE NOT NULL,
                          FOREIGN KEY (manager_id) REFERENCES managers (id) ON DELETE SET NULL
);

-- Создание таблицы связей между employees и departments
CREATE TABLE employee_department (
                                     employee_id BIGINT NOT NULL,
                                     department_id BIGINT NOT NULL,
                                     PRIMARY KEY (employee_id, department_id),
                                     FOREIGN KEY (employee_id) REFERENCES employee (id) ON DELETE CASCADE,
                                     FOREIGN KEY (department_id) REFERENCES departments (id) ON DELETE CASCADE
);