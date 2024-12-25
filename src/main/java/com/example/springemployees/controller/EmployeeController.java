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

import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService service;


    @GetMapping
    public Page<EmployeeDTO> findAllEmployee(
            @RequestParam Map<String, String> params, // Фильтры передаются через параметры запроса
            Pageable pageable // Для сортировки и пагинации
    ) {
        return service.findEmployeesByCriteria(params, pageable);
    }

    @PostMapping("/save_employee")
    public String saveEmployee(@Valid @RequestBody EmployeeDTO employee) {
        service.saveEmployee(employee);
        return "employee successfully saved";
    }

    @PutMapping("/update_employee")
    public EmployeeDTO updateEmployee(@Valid @RequestBody EmployeeDTO employee) {
        return service.updateEmployee(employee);
    }

    @DeleteMapping("delete_employee/{email}")
    public void deleteEmployee(@PathVariable String email) {
        service.deleteEmployee(email);
    }

    @GetMapping("/search")
    public Page<EmployeeDTO> searchEmployees(
            @RequestParam Map<String, String> params, // Фильтры передаются через параметры запроса
            Pageable pageable // Для сортировки и пагинации
    ) {
        return service.findEmployeesByCriteria(params, pageable);
    }
}