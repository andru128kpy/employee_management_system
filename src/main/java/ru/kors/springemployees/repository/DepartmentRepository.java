package ru.kors.springemployees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kors.springemployees.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
