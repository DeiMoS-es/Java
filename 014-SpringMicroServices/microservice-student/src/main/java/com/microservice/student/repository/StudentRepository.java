package com.microservice.student.repository;

import com.microservice.student.entities.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    // Nota, probar si se puede hacer on JpaRepository
    // Query method para buscar todos los cursos por ID, revisar que sea la nomenclatura de la tabla de BBDD
    //List<Student> findAllByCourseId(Long courseId);
    // La siguiente forma es una forma similar de hacer lo de arriba
    @Query("SELECT s FROM Student s WHERE s.courseId = :courseId")
    List<Student> findAllStudent(Long courseId);
}
