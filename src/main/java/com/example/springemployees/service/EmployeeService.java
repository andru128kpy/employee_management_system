package com.example.springemployees.service;

import com.example.springemployees.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAllEmployee();
    Employee saveEmployee(Employee employee);
    Employee findByEmail(String email);
    Employee updateEmployee(Employee employee);
    void deleteEmployee(String email);
    List<Employee> findByName(String firstName);
    List<Employee> findByStatus(String status);
    List<Employee> findByManager(String manager);
}
