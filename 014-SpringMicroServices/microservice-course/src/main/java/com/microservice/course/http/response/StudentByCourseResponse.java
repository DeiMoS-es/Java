package com.microservice.course.http.response;

import com.microservice.course.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Clase encargada de mapear la respuesta a la persona que consulta el microservicio
 * A la persona que haga la petición al microservicio cursos, se le va a responder con el nombre del curso, el profesor y la lista de estudiantes
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentByCourseResponse {
    private String nameCourse;
    private String teacher;
    private List<StudentDTO> studentDTOList;
}
