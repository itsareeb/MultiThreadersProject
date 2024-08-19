package com.hsbc.exceptions;

public class SlotAlreadyBookedException extends Exception {
    public SlotAlreadyBookedException(String message) {
        super(message);
    }
}
