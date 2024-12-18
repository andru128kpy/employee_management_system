package com.example.springemployees.service;

import com.example.springemployees.model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAllDepartments();
    Department saveDepartment(Department department);
    Department findById(Long id);
}
