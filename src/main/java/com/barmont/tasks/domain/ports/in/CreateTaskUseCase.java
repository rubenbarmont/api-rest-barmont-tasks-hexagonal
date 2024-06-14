package com.barmont.tasks.domain.ports.in;

import com.barmont.tasks.domain.models.Task;

public interface CreateTaskUseCase {
    Task createTask(Task task);
}
