package com.microservice.course.services;

import com.microservice.course.entities.Course;
import com.microservice.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Override
    public List<Course> findAllCourse() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course findByCourseId(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow();
    }

    @Override
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }
}
