package com.example.springemployees.mapper;

import com.example.springemployees.DTO.ManagerDTO;
import com.example.springemployees.model.Manager;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ManagerMapper {
    ManagerDTO toDto(Manager manager);

    Manager toEntity(ManagerDTO managerDTO);
}
