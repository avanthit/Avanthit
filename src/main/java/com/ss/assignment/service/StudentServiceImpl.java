package com.ss.assignment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.assignment.entity.Course;
import com.ss.assignment.entity.Student;
import com.ss.assignment.exception.StudentRegistrationException;
import com.ss.assignment.model.RequestObject;
import com.ss.assignment.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public ResponseEntity<String> saveStudentInfo(RequestObject requestObject) {
        if (requestObject.getNames() != null && requestObject.getNames().size() > 0) {
            Student student = mapToCourses(requestObject);
            save(student);
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("studentInfoSaved with studentId: " + student.getStudentId());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body("improperRequestObject");
        }

    }

    @Transactional
    @Override
    public ResponseEntity<String> deleteStudentInfo(Long id) {
        studentRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("studentInfo deleted");

    }

    @Override
    public ResponseEntity<String> getStudentsInfo(String courseName) throws StudentRegistrationException {
        List<Student> allStudentsByCourseName = studentRepository.findAllStudentsByCourseName(courseName);
        String studentsInfoByCourseNameStr = null;
        try {
            studentsInfoByCourseNameStr = new ObjectMapper().writeValueAsString(allStudentsByCourseName);
        } catch (JsonProcessingException e) {
            throw new StudentRegistrationException(HttpStatus.NOT_FOUND.value(), e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(studentsInfoByCourseNameStr);
    }

    private Student mapToCourses(RequestObject requestObject) {
        Student student = new Student();
        student.setStudentName(requestObject.getName());
        Set<Course> courses = new HashSet<>();
        requestObject.getNames().forEach(requestCourseName -> {
            Course course = new Course();
            course.setCourseName(requestCourseName);
            courses.add(course);
        });
        student.setCourses(courses);
        return student;
    }

    @Transactional
    private void save(Student student) {
        studentRepository.save(student);
    }


}
