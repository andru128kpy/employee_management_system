package ru.kors.springemployees.service;

import ru.kors.springemployees.model.Emloyee;

import java.util.List;

public interface EmployeeService {
    List<Emloyee> findAllEmployee();
    Emloyee saveEmployee(Emloyee employee);
    Emloyee findByEmail(String email);
    Emloyee updateEmployee(Emloyee employee);
    void deleteEmployee(String email);
}
