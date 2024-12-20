package com.example.springemployees.service;

import com.example.springemployees.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Page<Employee> findAllEmployee(Pageable pageable); // Изменено на Page<Employee> и добавлен Pageable
    Employee saveEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    void deleteEmployee(String email);
    Page<Employee> findEmployeesByCriteria(Map<String, String> params, Pageable pageable);
}