package com.hsbc.exceptions;

public class NoPatientsFoundException extends Exception {
    public NoPatientsFoundException(String message) {
        super(message);
    }
}
