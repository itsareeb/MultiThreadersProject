package com.hsbc.exceptions;

public class AppointmentAlreadyExistsException extends Exception {
    public AppointmentAlreadyExistsException(String message) {
        super(message);
    }
}
