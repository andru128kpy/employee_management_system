package ru.kors.springemployees.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kors.springemployees.model.Emloyee;
import ru.kors.springemployees.service.EmployeeService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService service;

    @GetMapping
    public List<Emloyee> findAllStudent() {
        //todo
        return service.findAllEmployee();
    }

    @PostMapping("save_employee")
    public String saveStudent(@RequestBody Emloyee employee) {
        service.saveEmployee(employee);
        return "employee successfully saved";
    }

    @GetMapping("/{email}")
    public Emloyee findByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }
    // /api/v1/students/oleg12@gmail.com

    @PutMapping("update_employee")
    public Emloyee updateStudent(@RequestBody Emloyee employee) {
        return service.updateEmployee(employee);
    }

    @DeleteMapping("delete_employee/{email}")
    public void deleteEmployee(@PathVariable String email) {
        service.deleteEmployee(email);
    }
}
