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
        return service.findAllStudent();
    }

    @PostMapping("save_employee")
    public String saveStudent(@RequestBody Emloyee student) {
        service.saveEmployee(student);
        return "Student successfully saved";
    }

    @GetMapping("/{email}")
    public Emloyee findByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }
    // /api/v1/students/oleg12@gmail.com

    @PutMapping("update_employee")
    public Emloyee updateStudent(@RequestBody Emloyee student) {
        return service.updateStudent(student);
    }

    @DeleteMapping("delete_employee/{email}")
    public void deleteStudent(@PathVariable String email) {
        service.deleteStudent(email);
    }
}
