package com.example.springemployees.controller;

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
    public List<Department> findAllDepartments() {
        return departmentService.findAllDepartments();
    }

    @PostMapping("save_department")
    public Department saveDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id) {
        return departmentService.findById(id);
    }
}
