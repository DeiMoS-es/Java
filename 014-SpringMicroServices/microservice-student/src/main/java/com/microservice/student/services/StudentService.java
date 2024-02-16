package com.microservice.student.services;

import com.microservice.student.entities.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAll();
    Student findByStudentId(Long studentId);
    void saveStudent(Student student);
    List<Student> findByCourseId(Long courseId);
}
