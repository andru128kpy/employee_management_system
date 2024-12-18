package ru.kors.springemployees.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kors.springemployees.model.Department;
import ru.kors.springemployees.repository.DepartmentRepository;
import ru.kors.springemployees.service.DepartmentService;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public List<Department> findAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department findById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }
}
