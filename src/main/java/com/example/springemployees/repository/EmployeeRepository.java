package com.example.springemployees.repository;

import com.example.springemployees.model.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.example.springemployees.model.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e LEFT JOIN e.departments d WHERE e.deleted = false")
    Page<Employee> findAllEmployeesWithDepartments(Pageable pageable);

    @Modifying
    @Query("UPDATE Employee e SET e.deleted = true WHERE e.email = :email")
    void softDeleteByEmail(String email);

    @Query("SELECT e FROM Employee e WHERE e.email = :email AND e.deleted = false")
    Optional<Employee> findByEmail(String email);

    Page<Employee> findAll(Specification<Employee> spec, Pageable pageable);

}