package com.example.springemployees.mapper;

import com.example.springemployees.DTO.DepartmentDTO;
import com.example.springemployees.model.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentDTO toDto(Department department);

    Department toEntity(DepartmentDTO departmentDTO);
}
