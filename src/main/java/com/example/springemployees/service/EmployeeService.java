package com.example.springemployees.service;

import com.example.springemployees.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.Map;

public interface EmployeeService {
    List<Employee> findAllEmployee(Sort sort);
    Employee saveEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    void deleteEmployee(String email);
    List<Employee> findEmployeesByCriteria(Map<String, String> params, Sort sort);
}
