package com.example.springemployees.mapper;

import com.example.springemployees.DTO.EmployeeDTO;
import com.example.springemployees.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DepartmentMapper.class, ManagerMapper.class})
public interface EmployeeMapper {

    @Mapping(target = "departments", source = "departments")
    @Mapping(target = "manager", source = "manager")
    EmployeeDTO toDto(Employee employee);

    @Mapping(target = "departments", source = "departments")
    @Mapping(target = "manager", source = "manager")
    Employee toEntity(EmployeeDTO employeeDTO);
}
