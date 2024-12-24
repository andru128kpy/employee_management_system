package com.example.springemployees.controller;

import com.example.springemployees.DTO.DepartmentDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import com.example.springemployees.model.Department;
import com.example.springemployees.service.DepartmentService;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/departments")
@AllArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    public List<DepartmentDTO> findAllDepartments() {
        return departmentService.findAllDepartments();
    }

    @PostMapping("save_department")
    public DepartmentDTO saveDepartment(@Valid @RequestBody DepartmentDTO department) {
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/{id}")
    public DepartmentDTO findById(@PathVariable Long id) {
        return departmentService.findById(id);
    }
}
