package com.example.springemployees.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.springemployees.model.Employee;
import com.example.springemployees.service.EmployeeService;

import java.util.List;


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

    @PostMapping("save_employee")
    public String saveStudent(@RequestBody Employee employee) {
        service.saveEmployee(employee);
        return "employee successfully saved";
    }

    @GetMapping("/{email}")
    public Employee findByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }
    // /api/v1/students/oleg12@gmail.com

    @PutMapping("update_employee")
    public Employee updateStudent(@RequestBody Employee employee) {
        return service.updateEmployee(employee);
    }

    @DeleteMapping("delete_employee/{email}")
    public void deleteEmployee(@PathVariable String email) {
        service.deleteEmployee(email);
    }

    @GetMapping("/findByName/{firstName}")
    public List<Employee> findByName(@PathVariable String firstName) {
        return service.findByName(firstName);
    }

    @GetMapping("/findByStatus/{status}")
    public List<Employee> findByStatus(@PathVariable String status) {
        return service.findByStatus(status);
    }

    @GetMapping("/findByManager/{manager}")
    public List<Employee> findByManager(@PathVariable String manager) {
        return service.findByManager(manager);
    }

    @GetMapping("/findByDepartmentName/{departmentName}")
    public List<Employee> findByDepartmentName(@PathVariable String departmentName) {
        return service.findByDepartmentName(departmentName);
    }

}
