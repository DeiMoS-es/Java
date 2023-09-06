package com.hexagonal.tasks.domain.ports.in;

import com.hexagonal.tasks.domain.models.Task;

import java.util.Optional;

public interface UpdateTaskUseCase {
    //A la hora de actualizar se le pasará un ID y como puede existir o no, utilizamos un Optional
    Optional<Task> updateTask(Long idTask, Task updateTask);
}
