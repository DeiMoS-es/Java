package com.microservice.course.services;

import com.microservice.course.client.StudentClient;
import com.microservice.course.dto.StudentDTO;
import com.microservice.course.entities.Course;
import com.microservice.course.http.response.StudentByCourseResponse;
import com.microservice.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentClient studentClient;
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

    @Override
    public StudentByCourseResponse findStudentsByCourseId(Long courseId) {
        // Consultar el curso
        Course course = courseRepository.findById(courseId).orElse(new Course()); // Si no lo encuentra genera un curso vacio
        // Obtener los estudiantes
        List<StudentDTO> studentDTOList = studentClient.findAllStudentByCourse(courseId);

        return StudentByCourseResponse.builder()
                .nameCourse(course.getNameCourse())
                .teacher(course.getTeacherCourse())
                .studentDTOList(studentDTOList)
                .build();
    }
}
