package com.hexagonal.tasks.application.usecases;

import com.hexagonal.tasks.domain.models.Task;
import com.hexagonal.tasks.domain.ports.in.CreateTaskUseCase;
import com.hexagonal.tasks.domain.ports.out.TaskRepositoryPort;

import java.time.LocalDateTime;

public class CreateTaskUseCaseImpl implements CreateTaskUseCase {
    private final TaskRepositoryPort taskRepositoryPort;

    public CreateTaskUseCaseImpl(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    @Override
    public Task createTask(Task task){
        // Asignar la fecha de creación actual si no está definida
        if (task.getCreationDate() == null) {
            task.setCreationDate(LocalDateTime.now());
        }
        return taskRepositoryPort.save(task);
    }

}
