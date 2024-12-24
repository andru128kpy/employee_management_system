package com.example.springemployees.service.Impl;

import com.example.springemployees.DTO.EmployeeDTO;
import com.example.springemployees.mapper.EmployeeMapper;
import com.example.springemployees.specification.EmployeeSpecifications;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.springemployees.model.Employee;
import com.example.springemployees.repository.EmployeeRepository;
import com.example.springemployees.service.EmployeeService;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Primary
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Page<EmployeeDTO> findAllEmployee(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = mapper.toEntity(employeeDTO);
        employee = repository.save(employee);
        return mapper.toDto(employee);
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        Employee employee = mapper.toEntity(employeeDTO);
        employee = repository.save(employee);
        return mapper.toDto(employee);
    }

    @Override
    public void deleteEmployee(String email) {
        repository.softDeleteByEmail(email);
    }

    @Override
    public Page<EmployeeDTO> findEmployeesByCriteria(Map<String, String> params, Pageable pageable) {
        String name = params.get("name");
        String status = params.get("status");

        // Парсим список departmentIds
        List<Long> departmentIds = null;
        if (params.containsKey("departmentIds")) {
            departmentIds = List.of(params.get("departmentIds").split(","))
                    .stream()
                    .map(Long::valueOf)
                    .toList();
        }

        // Создаем спецификацию
        Specification<Employee> specification = EmployeeSpecifications.combineFilters(name, status, departmentIds);

        // Фильтрация, сортировка и пагинация
        return repository.findAll(specification, pageable).map(mapper::toDto);
    }
}