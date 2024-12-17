package ru.kors.springemployees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kors.springemployees.model.Employee;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    void deleteByEmail(String email);
    Employee findEmployeeByEmail(String email);
    List<Employee> findByFirstName(String firstName);
    List<Employee> findByStatus(String status);
    List<Employee> findByManager(String manager);
}

