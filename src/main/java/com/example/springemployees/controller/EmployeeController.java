package com.example.springemployees.controller;

import com.example.springemployees.utils.SortUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
    public List<Employee> findAllEmployee(@RequestParam(required = false) String sortBy, @RequestParam(required = false) String sortDir) {
        Sort sort = SortUtil.buildSort(sortBy, sortDir);
        return service.findAllEmployee(sort);
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
    public List<Employee> searchEmployees(@RequestParam Map<String, String> params, @RequestParam(required = false) String sortBy, @RequestParam(required = false) String sortDir) {
        Sort sort = SortUtil.buildSort(sortBy, sortDir);
        return service.findEmployeesByCriteria(params, sort);
    }
}
