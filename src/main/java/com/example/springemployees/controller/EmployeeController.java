package com.example.springemployees.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.springemployees.model.Employee;
import com.example.springemployees.service.EmployeeService;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService service;

    @GetMapping
    public List<Employee> findAllStudent() {
        //todo
        return service.findAllEmployee();
    }

    @PostMapping("/save_employee")
    public String saveStudent(@Valid @RequestBody Employee employee) {
        service.saveEmployee(employee);
        return "employee successfully saved";
    }

    @PutMapping("/update_employee")
    public Employee updateStudent(@Valid @RequestBody Employee employee) {
        return service.updateEmployee(employee);
    }

    @DeleteMapping("delete_employee/{email}")
    public void deleteEmployee(@PathVariable String email) {
        service.deleteEmployee(email);
    }

    @GetMapping("/search")
    public List<Employee> searchEmployees(@RequestParam Map<String, String> params) {
        return service.findEmployeesByCriteria(params);
    }
}
