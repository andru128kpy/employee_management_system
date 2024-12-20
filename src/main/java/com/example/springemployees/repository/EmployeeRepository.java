package com.example.springemployees.repository;

import com.example.springemployees.model.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.example.springemployees.model.Employee;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.departments WHERE e.deleted = false")
    List<Employee> findAllEmployeesWithDepartments(Sort sort);

    @Modifying
    @Query("UPDATE Employee e SET e.deleted = true WHERE e.email = :email")
    void softDeleteByEmail(String email);

    default List<Employee> findEmployeesByCriteria(EntityManager em, Map<String, String> params, Sort sort) { // Добавлено Sort
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        Join<Employee, Department> departmentJoin = employeeRoot.join("departments", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(employeeRoot.get("deleted"), false));

        params.forEach((paramName, paramValue) -> {
            if (paramValue != null) {
                if ("departmentName".equals(paramName)) {
                    String[] departmentNames = paramValue.split(",");
                    predicates.add(criteriaBuilder.or(
                            List.of(departmentNames).stream()
                                    .map(String::trim)
                                    .map(name -> criteriaBuilder.equal(departmentJoin.get("name"), name))
                                    .toArray(Predicate[]::new)
                    ));
                } else {
                    predicates.add(criteriaBuilder.equal(employeeRoot.get(paramName), paramValue));
                }
            }
        });

        criteriaQuery.where(predicates.toArray(Predicate[]::new));
        criteriaQuery.orderBy(sort.get().map(order -> { // Применение сортировки
            if (order.isAscending()) {
                return criteriaBuilder.asc(employeeRoot.get(order.getProperty()));
            } else {
                return criteriaBuilder.desc(employeeRoot.get(order.getProperty()));
            }
        }).toList());


        return em.createQuery(criteriaQuery).getResultList();
    }
}

