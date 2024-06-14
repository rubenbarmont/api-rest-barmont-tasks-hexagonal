package com.barmont.tasks.domain.ports.in;

import com.barmont.tasks.domain.models.Task;

import java.util.Optional;

public interface UpdateTaskUseCase {
    // Él puso updatetask
    Optional<Task> updateTask(Long id, Task updateTask);
}
