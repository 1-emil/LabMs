package com.lab.employeeservice.employee.datalayer;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="employees")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //private identifier

    @Embedded
    private EmployeeIdentifier employeeIdentifier; //public identifier

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;

    private Double salary;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Embedded
    private Address address;


    public Employee() {
        this.employeeIdentifier = new EmployeeIdentifier();
    }

    public Employee(EmployeeIdentifier employeeIdentifier, String firstName, String lastName, String emailAddress, String phoneNumber) {
        this.employeeIdentifier = employeeIdentifier;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }
}