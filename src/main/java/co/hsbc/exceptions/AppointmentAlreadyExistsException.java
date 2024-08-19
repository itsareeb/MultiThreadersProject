package main.java.co.hsbc.exceptions;

public class AppointmentAlreadyExistsException extends Exception {
    public AppointmentAlreadyExistsException(String message) {
        super(message);
    }
}
