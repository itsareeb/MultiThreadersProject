package main.java.com.hsbc.exceptions;

public class PatientAlreadyExistsException extends Exception {
    public PatientAlreadyExistsException(String message) {
        super(message);
    }
}
