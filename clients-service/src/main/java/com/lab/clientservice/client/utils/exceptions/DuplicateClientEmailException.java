package com.lab.clientservice.client.utils.exceptions;

public class DuplicateClientEmailException extends RuntimeException{

        public DuplicateClientEmailException() {}

        public DuplicateClientEmailException(String message) { super(message); }

        public DuplicateClientEmailException(Throwable cause) { super(cause); }

        public DuplicateClientEmailException(String message, Throwable cause) { super(message, cause); }
}
