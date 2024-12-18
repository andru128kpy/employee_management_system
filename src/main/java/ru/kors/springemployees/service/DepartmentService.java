package ru.kors.springemployees.service;

import ru.kors.springemployees.model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAllDepartments();
    Department saveDepartment(Department department);
    Department findById(Long id);
}
