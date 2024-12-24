package com.example.springemployees.service.Impl;

import com.example.springemployees.DTO.DepartmentDTO;
import com.example.springemployees.mapper.DepartmentMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.springemployees.model.Department;
import com.example.springemployees.repository.DepartmentRepository;
import com.example.springemployees.service.DepartmentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository repository;
    private final DepartmentMapper mapper;

    @Override
    public List<DepartmentDTO> findAllDepartments() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        Department department = mapper.toEntity(departmentDTO);
        department = repository.save(department);
        return mapper.toDto(department);
    }

    @Override
    public DepartmentDTO findById(Long id) {
        Department department = repository.findById(id).orElse(null);
        return mapper.toDto(department);
    }
}
