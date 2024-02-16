package com.microservice.student.controller;

import com.microservice.student.entities.Student;
import com.microservice.student.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
    }
    @GetMapping("search/{studentId}")
    // Si no funciona cambiar Student por ?, para indicar una respuesta genérica
    public ResponseEntity<Student> findById(@PathVariable Long studentId){
        return ResponseEntity.ok(studentService.findByStudentId(studentId));
    }
    @GetMapping("/all")
    public  ResponseEntity<List<Student>> findAllStudent(){
        return ResponseEntity.ok(studentService.findAll());
    }
    @GetMapping("/search-by-course/{courseId}")
    public ResponseEntity<?> findByCourseId(@PathVariable Long courseId){
        return ResponseEntity.ok(studentService.findByCourseId(courseId));
    }
}
