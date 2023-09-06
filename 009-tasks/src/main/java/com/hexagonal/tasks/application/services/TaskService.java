package com.hexagonal.tasks.application.services;

import com.hexagonal.tasks.domain.models.AdditionalTaskInfo;
import com.hexagonal.tasks.domain.models.Task;
import com.hexagonal.tasks.domain.ports.in.*;

import java.util.List;
import java.util.Optional;

/**
 * Se podría crear solamente una interfaz con los métodos de crear, eliminar..etc
 */
public class TaskService implements CreateTaskUseCase, DeleteTaskUseCase, GetAdditionalTaskInfoUseCase, RetrieveTaskUseCase, UpdateTaskUseCase {
    private final CreateTaskUseCase createTaskUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;
    private final GetAdditionalTaskInfoUseCase getAdditionalTaskInfoUseCase;
    private final RetrieveTaskUseCase retrieveTaskUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;
    public TaskService(CreateTaskUseCase createTaskUseCase, DeleteTaskUseCase deleteTaskUseCase, GetAdditionalTaskInfoUseCase getAdditionalTaskInfoUseCase,
                       RetrieveTaskUseCase retrieveTaskUseCase, UpdateTaskUseCase updateTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
        this.deleteTaskUseCase = deleteTaskUseCase;
        this.getAdditionalTaskInfoUseCase = getAdditionalTaskInfoUseCase;
        this.retrieveTaskUseCase = retrieveTaskUseCase;
        this.updateTaskUseCase = updateTaskUseCase;
    }

    @Override
    public Task createTask(Task task) {
        return createTaskUseCase.createTask(task);
    }

    @Override
    public boolean deleteTask(Long idTask) {
        return deleteTaskUseCase.deleteTask(idTask);
    }

    @Override
    public AdditionalTaskInfo getAdditionalTaskInfo(Long userId) {
        return getAdditionalTaskInfoUseCase.getAdditionalTaskInfo(userId);
    }

    @Override
    public Optional<Task> getTask(Long idTask) {
        return retrieveTaskUseCase.getTask(idTask);
    }

    @Override
    public List<Task> getAllTask() {
        return retrieveTaskUseCase.getAllTask();
    }

    @Override
    public Optional<Task> updateTask(Long idTask, Task updateTask) {
        return updateTaskUseCase.updateTask(idTask, updateTask);
    }
}
