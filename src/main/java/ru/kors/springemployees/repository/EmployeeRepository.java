package ru.kors.springemployees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kors.springemployees.model.Employee;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    void deleteByEmail(String email);
    Employee findEmployeeByEmail(String email);
    List<Employee> findByFirstName(String firstName);
    List<Employee> findByStatus(String status);
    List<Employee> findByManager(String manager);

    @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.departments")
    List<Employee> findAllEmployeesWithDepartments();
}

