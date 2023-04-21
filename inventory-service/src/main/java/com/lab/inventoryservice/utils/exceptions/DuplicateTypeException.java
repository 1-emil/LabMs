package com.lab.inventoryservice.utils.exceptions;

public class DuplicateTypeException extends RuntimeException{

        public DuplicateTypeException() {}

        public DuplicateTypeException(String message) { super(message); }

        public DuplicateTypeException(Throwable cause) { super(cause); }

        public DuplicateTypeException(String message, Throwable cause) { super(message, cause); }
}
