package com.lab.employeeservice.employee.presentationlayer;

import com.lab.employeeservice.employee.businesslayer.EmployeeService;
import com.lab.employeeservice.employee.datalayer.Employee;;
import com.lab.employeeservice.employee.datalayer.EmployeeIdentifier;
import com.lab.employeeservice.employee.utils.exceptions.InvalidInputException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public List<EmployeeResponseModel> getEmployees(){
        return employeeService.getEmployees();
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployeeByEmployeeId(@PathVariable String employeeId){
        return employeeService.getEmployeeByEmployeeId(employeeId);
    }

    @PostMapping()
    public Employee addEmployee(@RequestBody Employee newEmployee){
        if(!newEmployee.getEmailAddress().contains("@") || !newEmployee.getEmailAddress().contains(".")){
            throw new InvalidInputException("Invalid employee email address");
        }

        return employeeService.addEmployee(newEmployee);
    }

    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable String employeeId){
        return employeeService.updateEmployee(employee, employeeId);
    }

    @DeleteMapping("/{employeeId}")
    public void removeEmployee(@PathVariable String employeeId){
        employeeService.removeEmployee(employeeId);
    }
}
