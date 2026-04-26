package com.hexagonal.tasks.domain.ports.in;

import com.hexagonal.tasks.domain.models.Task;

import java.util.Optional;

public interface UpdateTaskUseCase {
    //we need an id to update thas why it is optional
    Optional<Task>updateTask(Long id, Task updateTask);
}
