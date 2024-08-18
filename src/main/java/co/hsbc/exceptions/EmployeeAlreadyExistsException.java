package main.java.co.hsbc.exceptions;

public class EmployeeAlreadyExistsException extends Exception {
    public EmployeeAlreadyExistsException(String message) {
        super(message);
    }
}
