package com.hexagonal.tasks.infrastructure.entities;

import com.hexagonal.tasks.domain.models.Task;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private boolean completed;

    //Método estático para darle los valores de los geters del dominio
    public static TaskEntity fromDomainModel(Task task){
        return new TaskEntity(task.getIdTask(), task.getTitle(), task.getDescription(), task.getCreationDate(), task.isCompleted());
    }
    public Task toDomainModel(){
        return new Task(id, title, description, LocalDateTime.now(), completed);
    }
}
