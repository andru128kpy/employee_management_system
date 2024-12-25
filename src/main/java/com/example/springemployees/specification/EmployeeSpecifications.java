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
    // Фильтрация по имени
    public static Specification<Employee> filterByName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null || name.isEmpty() ? null :
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + name.toLowerCase() + "%");
    }

    // Фильтрация по статусу
    public static Specification<Employee> filterByStatus(String status) {
        return (root, query, criteriaBuilder) ->
                status == null || status.isEmpty() ? null :
                        criteriaBuilder.equal(root.get("status"), status);
    }

    // Фильтрация по департаментам
    public static Specification<Employee> filterByDepartments(java.util.List<Long> departmentIds) {
        return (root, query, criteriaBuilder) -> {
            if (departmentIds == null || departmentIds.isEmpty()) {
                return null;
            }
            var departmentsJoin = root.join("departments");
            return departmentsJoin.get("id").in(departmentIds);
        };
    }

    // Фильтрация по имени менеджера
    public static Specification<Employee> filterByManagerName(String managerName) {
        return (root, query, criteriaBuilder) -> {
            if (managerName == null || managerName.isEmpty()) {
                return null;
            }
            var managerJoin = root.join("manager"); // Джоин с таблицей менеджеров
            return criteriaBuilder.like(criteriaBuilder.lower(managerJoin.get("name")), "%" + managerName.toLowerCase() + "%");
        };
    }

    // Фильтрация по названию отдела
    public static Specification<Employee> filterByDepartmentName(String departmentName) {
        return (root, query, criteriaBuilder) -> {
            if (departmentName == null || departmentName.isEmpty()) {
                return null;
            }
            var departmentsJoin = root.join("departments"); // Джоин с таблицей департаментов
            return criteriaBuilder.like(criteriaBuilder.lower(departmentsJoin.get("name")), "%" + departmentName.toLowerCase() + "%");
        };
    }

    // Комбинирование фильтров
    public static Specification<Employee> combineFilters(String name, String status, java.util.List<Long> departmentIds, String managerName, String departmentName) {
        return Specification.where(filterByName(name))
                .and(filterByStatus(status))
                .and(filterByDepartments(departmentIds))
                .and(filterByManagerName(managerName))
                .and(filterByDepartmentName(departmentName));
    }
}
