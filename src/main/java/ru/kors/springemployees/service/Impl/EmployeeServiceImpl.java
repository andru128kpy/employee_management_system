package ru.kors.springemployees.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kors.springemployees.model.Emloyee;
import ru.kors.springemployees.repository.EmployeeRepository;
import ru.kors.springemployees.service.EmployeeService;

import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    @Override
    public List<Emloyee> findAllEmployee() {
        return repository.findAll();
    }

    @Override
    public Emloyee saveEmployee(Emloyee employee) {
        return repository.save(employee);
    }

    @Override
    public Emloyee findByEmail(String email) {
        return repository.findEmployeeByEmail(email);
    }

    @Override
    public Emloyee updateEmployee(Emloyee employee) {
        return repository.save(employee);
    }

    @Override
    @Transactional
    public void deleteEmployee(String email) {
        repository.deleteByEmail(email);
    }
}
