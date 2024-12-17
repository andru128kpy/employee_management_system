package ru.kors.springemployees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kors.springemployees.model.Emloyee;


public interface EmployeeRepository extends JpaRepository<Emloyee, Long> {
    void deleteByEmail(String email);
    Emloyee findEmployeeByEmail(String email);
}
