package com.microservice.course.services;

import com.microservice.course.entities.Course;

import java.util.List;

public interface CourseService {

    List<Course> findAllCourse();
    Course findByCourseId(Long courseId);
    void saveCourse(Course course);
}
