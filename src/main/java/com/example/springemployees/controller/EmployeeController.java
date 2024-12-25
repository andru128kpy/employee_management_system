package com.example.springemployees.controller;

import com.example.springemployees.DTO.EmployeeDTO;
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
public class EmployeeController {
    private final EmployeeService service;


    @PostMapping("/{email}/photo")
    public String uploadPhoto(
            @PathVariable String email, @RequestParam("photo") MultipartFile file
    ) throws IOException {
        service.uploadPhoto(email, file);
        return "Photo successfully uploaded";
    }

    @GetMapping
    public Page<EmployeeDTO> findAllEmployees(
            @RequestParam Map<String, String> params, // Фильтры передаются через параметры запроса
            Pageable pageable // Для сортировки и пагинации
    ) {
        return service.findEmployeesByCriteria(params, pageable);
    }

    @PostMapping
    public String createEmployee(@Valid @RequestBody EmployeeDTO employee) {
        service.saveEmployee(employee);
        return "Employee successfully created";
    }

    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employee) {
        return service.updateEmployee(employee);
    }

    @DeleteMapping("/{email}")
    public void deleteEmployee(@PathVariable String email) {
        service.deleteEmployee(email);
    }
}