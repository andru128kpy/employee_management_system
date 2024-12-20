package com.example.springemployees.controller;

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
    public Page<Employee> findAllEmployee(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) { // Изменено на Page<Employee> и добавлен Pageable
        return service.findAllEmployee(pageable);
    }

    @PostMapping("/save_employee")
    public String saveEmployee(@Valid @RequestBody Employee employee) {
        service.saveEmployee(employee);
        return "employee successfully saved";
    }

    @PutMapping("/update_employee")
    public Employee updateEmployee(@Valid @RequestBody Employee employee) {
        return service.updateEmployee(employee);
    }

    @DeleteMapping("delete_employee/{email}")
    public void deleteEmployee(@PathVariable String email) {
        service.deleteEmployee(email);
    }

    @GetMapping("/search")
    public Page<Employee> searchEmployees(@RequestParam Map<String, String> params, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) { // Изменено на Page<Employee> и добавлен Pageable
        return service.findEmployeesByCriteria(params, pageable);
    }
}