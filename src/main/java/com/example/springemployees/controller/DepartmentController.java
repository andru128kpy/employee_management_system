package com.example.springemployees.controller;

import com.example.springemployees.DTO.DepartmentDTO;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Get all departments", description = "Returns a list of all departments")
    @GetMapping
    public List<DepartmentDTO> findAllDepartments() {
        return departmentService.findAllDepartments();
    }

    @Operation(summary = "Save department", description = "Creates or maintains a department")
    @PostMapping("save_department")
    public DepartmentDTO saveDepartment(@Valid @RequestBody DepartmentDTO department) {
        return departmentService.saveDepartment(department);
    }

    @Operation(summary = "Get department by ID", description = "Returns the department by its ID")
    @GetMapping("/{id}")
    public DepartmentDTO findById(@PathVariable Long id) {
        return departmentService.findById(id);
    }
}
