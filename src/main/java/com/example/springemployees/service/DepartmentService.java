package com.example.springemployees.service;

import com.example.springemployees.DTO.DepartmentDTO;
import com.example.springemployees.model.Department;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDTO> findAllDepartments();
    DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);
    DepartmentDTO findById(Long id);
}
