package com.ss.assignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.assignment.exception.StudentRegistrationException;
import com.ss.assignment.model.RequestObject;
import com.ss.assignment.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/v1")
public class StudentRegistrationController {

    private final StudentService studentService;

    public StudentRegistrationController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/student/{courseName}")
    public ResponseEntity<String> getStudentsInfo(@PathVariable(name = "courseName") String courseName) throws StudentRegistrationException {
        return studentService.getStudentsInfo(courseName);
    }

    @PostMapping(value = "/student")
    public ResponseEntity<String> saveStudentInfo(HttpServletRequest httpServletRequest) throws IOException {
        RequestObject requestObject = getRequestObject(httpServletRequest);
        return studentService.saveStudentInfo(requestObject);
    }

    @DeleteMapping(value = "/student/{id}")
    public ResponseEntity<String> deleteStudentInfo(@PathVariable("id") Long id) {
        return studentService.deleteStudentInfo(id);
    }

    private RequestObject getRequestObject(HttpServletRequest httpServletRequest) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(httpServletRequest.getInputStream(), RequestObject.class);
    }
}
