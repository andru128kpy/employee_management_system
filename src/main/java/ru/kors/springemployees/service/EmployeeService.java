package ru.kors.springemployees.service;

import ru.kors.springemployees.model.Emloyee;

import java.util.List;

public interface EmployeeService {
    List<Emloyee> findAllStudent();
    Emloyee saveEmployee(Emloyee student);
    Emloyee findByEmail(String email);
    Emloyee updateStudent(Emloyee student);
    void deleteStudent(String email);
}
