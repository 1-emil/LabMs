package com.lab.employeeservice.employee.businesslayer;

import com.lab.employeeservice.employee.datalayer.Employee;
import com.lab.employeeservice.employee.employeemapper.EmployeeRepository;
import com.lab.employeeservice.employee.employeemapper.EmployeeResponseMapper;
import com.lab.employeeservice.employee.presentationlayer.EmployeeResponseModel;
import com.lab.employeeservice.employee.utils.exceptions.DuplicateEmployeeEmailException;
import com.lab.employeeservice.employee.utils.exceptions.InvalidInputException;
import com.lab.employeeservice.employee.utils.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;
    private EmployeeResponseMapper employeeResponseMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeResponseMapper employeeResponseMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeResponseMapper = employeeResponseMapper;
    }

    @Override
    public List<EmployeeResponseModel> getEmployees() {
        return employeeResponseMapper.entityListToResponseModelList(employeeRepository.findAll());
    }

    @Override
    public Employee getEmployeeByEmployeeId(String employeeId) {
        Employee employee = employeeRepository.findByEmployeeIdentifier_EmployeeId(employeeId);
        if (employee == null) {
            throw new NotFoundException("Employee not found");
        }
        return employeeRepository.findByEmployeeIdentifier_EmployeeId(employeeId);
    }

    @Override
    public Employee addEmployee(Employee newEmployee) {
        try {
            return employeeRepository.save(newEmployee);
        }
        catch (Exception ex){
            if(ex.getMessage().contains("constraint [email_address]") || ex.getCause().toString().contains("must be unique")){
                throw new DuplicateEmployeeEmailException("Employee with email address " + newEmployee.getEmailAddress() + " already exists");

            }
            throw new InvalidInputException("An unknown error has occured");
        }
    }

    @Override
    public Employee updateEmployee(Employee employee, String employeeId) {
        //find if employee exists
        Employee existingEmployee = employeeRepository.findByEmployeeIdentifier_EmployeeId(employeeId);

        if (existingEmployee == null){
            return null; //later on throw an exception
        }

        employee.setId(existingEmployee.getId());
        employee.setEmployeeIdentifier(existingEmployee.getEmployeeIdentifier());

        return employeeRepository.save(employee);
    }

    @Override
    public void removeEmployee(String employeeId) {
        //find if employee exists
        Employee existingEmployee = employeeRepository.findByEmployeeIdentifier_EmployeeId(employeeId);

        if (existingEmployee == null){
            return; //later on throw an exception
        }

        employeeRepository.delete(existingEmployee);
    }
}
