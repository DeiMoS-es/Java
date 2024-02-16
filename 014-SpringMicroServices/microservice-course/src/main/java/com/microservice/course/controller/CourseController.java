package com.microservice.course.controller;

import com.microservice.course.entities.Course;
import com.microservice.course.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStudent(@RequestBody Course course) {
        courseService.saveCourse(course);
    }
    @GetMapping("/all")
    public  ResponseEntity<List<Course>> findAllStudent(){
        return ResponseEntity.ok(courseService.findAllCourse());
    }
    @GetMapping("search/{courseId}")
    // Si no funciona cambiar Student por ?, para indicar una respuesta genérica
    public ResponseEntity<Course> findById(@PathVariable Long course){
        return ResponseEntity.ok(courseService.findByCourseId(course));
    }
    @GetMapping("/search-student/{courseId")
    public ResponseEntity<?> findStudentByCourseId(@PathVariable Long courseId){
        return ResponseEntity.ok(courseService.findStudentsByCOurseId(courseId));
    }
}
