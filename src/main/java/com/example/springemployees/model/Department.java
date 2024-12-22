package com.example.springemployees.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Data
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_seq")
    @SequenceGenerator(name = "department_seq", sequenceName = "departments_SEQ", allocationSize = 1)
    private Long id;

    @NotBlank(message = "The department name cannot be empty")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "departments")
    private Set<Employee> employees;

}
