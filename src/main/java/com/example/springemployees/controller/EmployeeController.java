package com.example.springemployees.controller;

import com.example.springemployees.DTO.EmployeeDTO;
import com.example.springemployees.components.EmployeeValidationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import com.example.springemployees.model.Employee;
import com.example.springemployees.service.EmployeeService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
@Tag(name = "Employee Controller", description = "Employee management")
public class EmployeeController {
    private final EmployeeService service;
    private final EmployeeValidationService validationService;

    @Operation(summary = "Upload employee photo", description = "Uploads a photo for an employee via email")
    @PostMapping("/{email}/photo")
    public String uploadPhoto(
            @PathVariable String email, @RequestParam("photo") MultipartFile file
    ) throws IOException {
        service.uploadPhoto(email, file);
        return "Photo successfully uploaded";
    }
    @Operation(summary = "Get list of employees", description = "Returns a paginated list of employees with filters")
    @GetMapping
    public Page<EmployeeDTO> findAllEmployees(
            @RequestParam Map<String, String> params,
            Pageable pageable
    ) {
        validationService.validateSearchParams(params);
        return service.findEmployeesByCriteria(params, pageable);
    }

    @Operation(summary = "Create an employee", description = "Creating a new employee")
    @PostMapping
    public String createEmployee(@Valid @RequestBody EmployeeDTO employee) {
        service.saveEmployee(employee);
        return "Employee successfully created";
    }

    @Operation(summary = "Update employee information", description = "Updates employee data by ID")
    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employee) {
        return service.updateEmployee(employee);
    }

    @Operation(summary = "Удалить сотрудника", description = "Удаляет сотрудника по email")
    @DeleteMapping("/{email}")
    public void deleteEmployee(@PathVariable String email) {
        service.deleteEmployee(email);
    }
}