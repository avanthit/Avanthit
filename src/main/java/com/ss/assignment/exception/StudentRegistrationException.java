package com.ss.assignment.exception;

public class StudentRegistrationException extends Exception{

    private static final long serialVersion = 1L;
    private final int status;
    private final String message;

    public StudentRegistrationException(int status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    @Override
    public String getMessage() { return message; }

    public int getStatus() { return status;}
}
