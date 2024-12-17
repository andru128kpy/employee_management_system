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
    public List<Emloyee> findAllStudent() {
        return repository.findAll();
    }

    @Override
    public Emloyee saveEmployee(Emloyee student) {
        return repository.save(student);
    }

    @Override
    public Emloyee findByEmail(String email) {
        return repository.findStudentByEmail(email);
    }

    @Override
    public Emloyee updateStudent(Emloyee student) {
        return repository.save(student);
    }

    @Override
    @Transactional
    public void deleteStudent(String email) {
        repository.deleteByEmail(email);
    }
}
