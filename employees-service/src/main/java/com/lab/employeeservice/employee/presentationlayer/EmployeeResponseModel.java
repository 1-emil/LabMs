package com.lab.employeeservice.employee.presentationlayer;


import com.lab.employeeservice.employee.datalayer.Department;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeResponseModel {

    private final String employeeId;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final String streetAddress;
    private final String city;
    private final String province;
    private final String country;
    private final String postalCode;
    private final String phoneNumber;
    private final Double salary;
    private final Department department;
}

