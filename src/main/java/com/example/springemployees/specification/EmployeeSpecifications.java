package com.example.springemployees.specification;

import com.example.springemployees.model.Department;
import com.example.springemployees.model.Employee;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;
import java.util.Map;

public class EmployeeSpecifications {
    public static Specification<Employee> filterByName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null || name.isEmpty() ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Employee> filterByStatus(String status) {
        return (root, query, criteriaBuilder) ->
                status == null || status.isEmpty() ? null : criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Employee> filterByDepartments(List<Long> departmentIds) {
        return (root, query, criteriaBuilder) -> {
            if (departmentIds == null || departmentIds.isEmpty()) {
                return null;
            }
            var departmentsJoin = root.join("departments", JoinType.LEFT);
            return departmentsJoin.get("id").in(departmentIds);
        };
    }

    public static Specification<Employee> combineFilters(String name, String status, List<Long> departmentIds) {
        return Specification.where(filterByName(name))
                .and(filterByStatus(status))
                .and(filterByDepartments(departmentIds));
    }
}
