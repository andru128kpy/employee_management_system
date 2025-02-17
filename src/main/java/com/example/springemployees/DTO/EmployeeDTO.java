package com.example.springemployees.DTO;

import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Data
public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private ManagerDTO manager;
    private String photoPath;
    private String status;
    private String email;
    private Set<DepartmentDTO> departments;
}