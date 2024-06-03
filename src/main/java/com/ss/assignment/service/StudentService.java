package com.ss.assignment.service;

import com.ss.assignment.exception.StudentRegistrationException;
import com.ss.assignment.model.RequestObject;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    ResponseEntity<String> saveStudentInfo(RequestObject requestObject);

    ResponseEntity<String> deleteStudentInfo(Long id);

    ResponseEntity<String> getStudentsInfo(String courseName) throws StudentRegistrationException;
}
