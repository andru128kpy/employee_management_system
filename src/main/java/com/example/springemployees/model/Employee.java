package com.example.springemployees.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "The name cannot be empty")
    private String firstName;

    @NotBlank(message = "The last name cannot be empty")
    private String lastName;

    @Past(message = "The date of birth must be in the past")
    @NotNull(message = "The date of birth cannot be empty")
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    private String photoPath;

    private String status;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean deleted = false;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(
            name = "employee_department",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    private Set<Department> departments;

}
