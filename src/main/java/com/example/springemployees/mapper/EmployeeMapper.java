package com.example.springemployees.mapper;

import com.example.springemployees.DTO.EmployeeDTO;
import com.example.springemployees.model.Employee;
import com.example.springemployees.model.Manager;
import org.mapstruct.*;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {DepartmentMapper.class, ManagerMapper.class})
public interface EmployeeMapper {

    @Mapping(target = "departments", source = "departments") // Полный маппинг департаментов
    @Mapping(target = "manager", source = "manager")         // Полный маппинг менеджера
    EmployeeDTO toDto(Employee employee);

    @Mapping(target = "departments", source = "departments") // Обратный маппинг департаментов
    @Mapping(target = "manager", source = "manager")         // Обратный маппинг менеджера
    Employee toEntity(EmployeeDTO employeeDTO);
}
