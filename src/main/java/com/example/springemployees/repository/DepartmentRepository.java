package com.example.springemployees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springemployees.model.Department;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findDepartmentById(Long id);
}

