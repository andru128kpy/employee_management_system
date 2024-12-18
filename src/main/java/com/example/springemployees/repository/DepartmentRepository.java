package com.example.springemployees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springemployees.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
