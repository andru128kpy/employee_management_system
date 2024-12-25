package com.example.springemployees.service;

import com.example.springemployees.DTO.EmployeeDTO;
import com.example.springemployees.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Page<EmployeeDTO> findAllEmployee(Pageable pageable);
    void saveEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO);
    void deleteEmployee(String email);
    Page<EmployeeDTO> findEmployeesByCriteria(Map<String, String> params, Pageable pageable);
}