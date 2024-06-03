package com.ss.assignment.repository;

import com.ss.assignment.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select sd from student_details sd join STUDENT_COURSE_MAPPING scm on sd.student_id = scm.student_id " +
            "join course_details cd on scm.course_id = cd.course_id where cd.course_name = ?1 order by sd.student_name")
    List<Student> findAllStudentsByCourseName(String courseName);

    /*@Query("Delete sd from student_details sd where sd.student_id = ?1")
    void deleteById(Long studentId);*/
}
