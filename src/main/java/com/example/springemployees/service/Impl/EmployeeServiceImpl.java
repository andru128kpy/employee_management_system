package com.example.springemployees.service.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.springemployees.model.Employee;
import com.example.springemployees.repository.EmployeeRepository;
import com.example.springemployees.service.EmployeeService;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Primary
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Page<Employee> findAllEmployee(Pageable pageable) {
        return repository.findAllEmployeesWithDepartments(pageable);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }


    @Override
    public Employee updateEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    @Transactional
    public void deleteEmployee(String email) {
        repository.softDeleteByEmail(email);
    }

    @Override
    public Page<Employee> findEmployeesByCriteria(Map<String, String> params, Pageable pageable) {
        return repository.findEmployeesByCriteria(entityManager, params, pageable);
    }

}