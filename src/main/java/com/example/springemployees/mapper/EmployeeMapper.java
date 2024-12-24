package com.example.springemployees.mapper;

import com.example.springemployees.DTO.EmployeeDTO;
import com.example.springemployees.model.Employee;
import org.mapstruct.*;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {DepartmentMapper.class})
public interface EmployeeMapper {

    @Mapping(target = "departmentIds", source = "departments", qualifiedByName = "mapDepartmentsToIds")
    EmployeeDTO toDto(Employee employee);

    @Mapping(target = "departments", source = "departmentIds", qualifiedByName = "mapIdsToDepartments")
    Employee toEntity(EmployeeDTO employeeDTO);

    @Named("mapDepartmentsToIds")
    default Set<Long> mapDepartmentsToIds(Set<com.example.springemployees.model.Department> departments) {
        if (departments == null) {
            return null;
        }
        return departments.stream().map(com.example.springemployees.model.Department::getId).collect(Collectors.toSet());
    }

    @Named("mapIdsToDepartments")
    default Set<com.example.springemployees.model.Department> mapIdsToDepartments(Set<Long> ids) {
        if (ids == null) {
            return null;
        }
        return ids.stream().map(id -> {
            com.example.springemployees.model.Department department = new com.example.springemployees.model.Department();
            department.setId(id);
            return department;
        }).collect(Collectors.toSet());
    }
}
