package com.lab.employeeservice.employee.employeemapper;


import com.lab.employeeservice.employee.datalayer.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByEmployeeIdentifier_EmployeeId(String employeeId);
}