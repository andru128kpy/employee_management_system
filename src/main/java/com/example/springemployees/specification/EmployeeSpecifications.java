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
    public static Specification<Employee> hasDepartmentName(String departmentName) {
        return (root, query, criteriaBuilder) -> {
            if (departmentName == null || departmentName.isBlank()) {
                return null;
            }

            String[] departmentNames = departmentName.split(",");
            Join<Employee, Department> departmentJoin = root.join("departments", JoinType.LEFT);
            return criteriaBuilder.or(
                    List.of(departmentNames).stream()
                            .map(String::trim)
                            .map(name -> criteriaBuilder.equal(departmentJoin.get("name"), name))
                            .toArray(Predicate[]::new)
            );
        };
    }

    public static Specification<Employee> hasProperty(String propertyName, String propertyValue) {
        return (root, query, criteriaBuilder) -> {
            if (propertyValue == null || propertyValue.isBlank()) {
                return null;
            }
            return criteriaBuilder.equal(root.get(propertyName), propertyValue);
        };
    }

    public static Specification<Employee> isNotDeleted() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("deleted"), false);
    }

    public static Specification<Employee> combineSpecifications(Map<String, String> params) {
        Specification<Employee> spec = isNotDeleted();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if ("departmentName".equals(entry.getKey())) {
                spec = spec.and(hasDepartmentName(entry.getValue()));
            } else {
                spec = spec.and(hasProperty(entry.getKey(), entry.getValue()));
            }
        }
        return spec;
    }
}
