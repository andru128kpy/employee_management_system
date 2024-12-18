package com.example.springemployees.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "emloyees")
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

    private String manager;

    @URL(message = "The URL must be correct")
    private String urlPhoto;

    private String status;
    @Column(unique = true)
    private String email;
    @Transient
    private int age;

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
