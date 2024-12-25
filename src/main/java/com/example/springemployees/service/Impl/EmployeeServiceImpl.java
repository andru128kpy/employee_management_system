package com.example.springemployees.service.Impl;

import com.example.springemployees.DTO.EmployeeDTO;
import com.example.springemployees.mapper.EmployeeMapper;
import com.example.springemployees.model.Manager;
import com.example.springemployees.repository.DepartmentRepository;
import com.example.springemployees.repository.ManagerRepository;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Primary
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;
    private ManagerRepository managerRepository;
    private DepartmentRepository departmentRepository;
    private static final String UPLOAD_DIR = "uploads/";

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Page<EmployeeDTO> findAllEmployee(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public void saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = mapper.toEntity(employeeDTO);

        // Привязка менеджера
        if (employeeDTO.getManager() != null && employeeDTO.getManager().getId() != null) {
            Manager manager = managerRepository.findById(employeeDTO.getManager().getId())
                    .orElseThrow(() -> new RuntimeException("Manager not found"));
            employee.setManager(manager);
        }

        // Привязка департаментов
        if (employeeDTO.getDepartments() != null) {
            employee.setDepartments(
                    employeeDTO.getDepartments().stream()
                            .map(departmentDTO -> departmentRepository.findDepartmentById(departmentDTO.getId())
                                    .orElseThrow(() -> new RuntimeException("Department not found")))
                            .collect(Collectors.toSet())
            );
        }

        repository.save(employee);
    }


        @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
            Employee employee = mapper.toEntity(employeeDTO);

            // Привязка менеджера
            if (employeeDTO.getManager() != null && employeeDTO.getManager().getId() != null) {
                Manager manager = managerRepository.findById(employeeDTO.getManager().getId())
                        .orElseThrow(() -> new RuntimeException("Manager not found"));
                employee.setManager(manager);
            }

            // Привязка департаментов
            if (employeeDTO.getDepartments() != null) {
                employee.setDepartments(
                        employeeDTO.getDepartments().stream()
                                .map(departmentDTO -> departmentRepository.findDepartmentById(departmentDTO.getId())
                                        .orElseThrow(() -> new RuntimeException("Department not found")))
                                .collect(Collectors.toSet())
                );
            }

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
        String managerName = params.get("managerName");
        String departmentName = params.get("departmentName");

        // Парсим список departmentIds
        List<Long> departmentIds = null;
        if (params.containsKey("departmentIds")) {
            departmentIds = List.of(params.get("departmentIds").split(","))
                    .stream()
                    .map(Long::valueOf)
                    .toList();
        }

        // Создаем спецификацию
        Specification<Employee> specification = EmployeeSpecifications.combineFilters(name, status, departmentIds, managerName, departmentName);

        // Фильтрация, сортировка и пагинация
        return repository.findAll(specification, pageable).map(mapper::toDto);
    }

    @Override
    public void uploadPhoto(String email, MultipartFile file) throws IOException {
        Employee employee = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee with email " + email + " not found"));

        // Проверяем, что файл не пустой
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty!");
        }

        // Создаем директорию, если она не существует
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Генерируем уникальное имя файла
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);

        // Сохраняем файл
        try {
            file.transferTo(filePath);
        } catch (IOException e) {
            throw new IOException("Failed to save file " + fileName + ": " + e.getMessage());
        }

        // Обновляем путь к фотографии у сотрудника
        employee.setPhotoPath(fileName); // Сохраняем относительный путь
        repository.save(employee);
    }
}