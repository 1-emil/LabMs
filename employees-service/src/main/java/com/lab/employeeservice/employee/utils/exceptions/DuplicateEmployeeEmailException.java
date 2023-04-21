package com.lab.employeeservice.employee.utils.exceptions;

public class DuplicateEmployeeEmailException extends RuntimeException {

        public DuplicateEmployeeEmailException() {}

        public DuplicateEmployeeEmailException(String message) { super(message); }

        public DuplicateEmployeeEmailException(Throwable cause) { super(cause); }

        public DuplicateEmployeeEmailException(String message, Throwable cause) { super(message, cause); }
}
