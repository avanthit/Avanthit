package com.ss.assignment.controller;

import com.ss.assignment.exception.StudentRegistrationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StudentRegistrationException.class)
    public ResponseEntity<String> handleStudentRegistrationException( StudentRegistrationException ex) {
        return ResponseEntity.status(ex.getStatus()).contentType(MediaType.APPLICATION_JSON).body(ex.getMessage());
    }
}
