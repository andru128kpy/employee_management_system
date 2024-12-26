package com.example.springemployees.specification;


import com.example.springemployees.model.Employee;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecifications {
    public static Specification<Employee> filterByName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null || name.isEmpty() ? null :
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Employee> filterByStatus(String status) {
        return (root, query, criteriaBuilder) ->
                status == null || status.isEmpty() ? null :
                        criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Employee> filterByDepartments(java.util.List<Long> departmentIds) {
        return (root, query, criteriaBuilder) -> {
            if (departmentIds == null || departmentIds.isEmpty()) {
                return null;
            }
            var departmentsJoin = root.join("departments");
            return departmentsJoin.get("id").in(departmentIds);
        };
    }

    public static Specification<Employee> filterByManagerName(String managerName) {
        return (root, query, criteriaBuilder) -> {
            if (managerName == null || managerName.isEmpty()) {
                return null;
            }
            var managerJoin = root.join("manager");
            return criteriaBuilder.like(criteriaBuilder.lower(managerJoin.get("name")), "%" + managerName.toLowerCase() + "%");
        };
    }

    public static Specification<Employee> filterByDepartmentName(String departmentName) {
        return (root, query, criteriaBuilder) -> {
            if (departmentName == null || departmentName.isEmpty()) {
                return null;
            }
            var departmentsJoin = root.join("departments");
            return criteriaBuilder.like(criteriaBuilder.lower(departmentsJoin.get("name")), "%" + departmentName.toLowerCase() + "%");
        };
    }

    public static Specification<Employee> combineFilters(String name, String status, java.util.List<Long> departmentIds, String managerName, String departmentName) {
        return Specification.where(filterByName(name))
                .and(filterByStatus(status))
                .and(filterByDepartments(departmentIds))
                .and(filterByManagerName(managerName))
                .and(filterByDepartmentName(departmentName));
    }
}
