package com.gigaspaces;

public class OperationFailedException extends Exception {
    public OperationFailedException(String message, Exception e) {
        super(message, e);
    }
    public OperationFailedException(String message) {
        super(message);
    }
}
