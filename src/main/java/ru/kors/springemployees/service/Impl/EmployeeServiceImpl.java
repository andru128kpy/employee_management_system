package ru.kors.springemployees.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kors.springemployees.model.Employee;
import ru.kors.springemployees.repository.EmployeeRepository;
import ru.kors.springemployees.service.EmployeeService;

import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    @Override
    public List<Employee> findAllEmployee() {
        return repository.findAllEmployeesWithDepartments();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Employee findByEmail(String email) {
        return repository.findEmployeeByEmail(email);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    @Transactional
    public void deleteEmployee(String email) {
        repository.deleteByEmail(email);
    }

    @Override
    public List<Employee> findByName(String firstName) {
        return repository.findByFirstName(firstName);
    }

    @Override
    public List<Employee> findByStatus(String status) {
        return repository.findByStatus(status);
    }

    @Override
    public List<Employee> findByManager(String manager) {
        return repository.findByManager(manager);
    }
}
