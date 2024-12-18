package ru.kors.springemployees.controller;

import lombok.AllArgsConstructor;
import ru.kors.springemployees.model.Department;
import ru.kors.springemployees.service.DepartmentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
@AllArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    public List<Department> findAllDepartments() {
        return departmentService.findAllDepartments();
    }

    @PostMapping("save_department")
    public Department saveDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id) {
        return departmentService.findById(id);
    }
}
