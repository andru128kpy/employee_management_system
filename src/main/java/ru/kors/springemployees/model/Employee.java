package ru.kors.springemployees.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@Data
@Entity
@Table(name = "emloyees")
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String manager;
    private String url;
    private String status;
    @Column(unique = true)
    private String email;
    @Transient
    private int age;

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
