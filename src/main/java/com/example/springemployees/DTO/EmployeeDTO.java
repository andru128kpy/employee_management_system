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
    private String manager;
    private String urlPhoto;
    private String status;
    private String email;
    private Set<Long> departmentIds;
}