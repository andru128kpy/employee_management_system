package com.example.springemployees.service;

import com.example.springemployees.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    List<Employee> findAllEmployee();
    Employee saveEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    void deleteEmployee(String email);
    List<Employee> findEmployeesByCriteria(Map<String, String> params);
}
