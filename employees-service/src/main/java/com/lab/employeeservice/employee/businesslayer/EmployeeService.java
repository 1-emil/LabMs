package com.lab.employeeservice.employee.businesslayer;



import com.lab.employeeservice.employee.datalayer.Employee;
import com.lab.employeeservice.employee.presentationlayer.EmployeeResponseModel;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponseModel> getEmployees();

    Employee getEmployeeByEmployeeId(String employeeId);

    Employee addEmployee(Employee newEmployee);

    Employee updateEmployee(Employee employee, String employeeId);

    void removeEmployee(String employeeId);

}
