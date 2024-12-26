-- Создание таблицы managers
CREATE TABLE managers (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL, -- @NotBlank
                          email VARCHAR(255) -- nullable, нет аннотации @NotBlank
);

-- Создание таблицы departments
CREATE TABLE departments (
                             id BIGSERIAL PRIMARY KEY,
                             name VARCHAR(255) NOT NULL, -- @NotBlank
                             CONSTRAINT departments_name_unique UNIQUE (name) -- уникальность имени, если предполагается
);

-- Создание таблицы employees
CREATE TABLE employee (
                          id BIGSERIAL PRIMARY KEY,
                          first_name VARCHAR(100) NOT NULL, -- @NotBlank
                          last_name VARCHAR(100) NOT NULL, -- @NotBlank
                          date_of_birth DATE NOT NULL, -- @Past и @NotNull
                          manager_id BIGINT, -- Внешний ключ на managers
                          photo_path VARCHAR(500), -- nullable, нет @NotBlank
                          status VARCHAR(100), -- nullable, нет ограничений
                          email VARCHAR(255) UNIQUE NOT NULL, -- @NotBlank и уникальность
                          deleted BOOLEAN DEFAULT FALSE NOT NULL, -- @Column(nullable = false) и @ColumnDefault("false")
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