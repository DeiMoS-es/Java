package com.hexagonal.tasks.domain.ports.out;

import com.hexagonal.tasks.domain.models.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepositoryPort {

    Task saveTask(Task task);
    Optional<Task> finByIdTask(Long idTask);
    List<Task> findAll();
    Optional<Task> updateTask(Task task);
    boolean deleteById(Long idTask);
}
