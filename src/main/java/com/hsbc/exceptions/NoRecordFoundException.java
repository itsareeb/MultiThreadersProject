package com.hsbc.exceptions;

public class NoRecordFoundException extends Exception{
    public NoRecordFoundException() {
        super();
    }

    public NoRecordFoundException(String message) {
        super(message);
    }

    public NoRecordFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoRecordFoundException(Throwable cause) {
        super(cause);
    }

    protected NoRecordFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
