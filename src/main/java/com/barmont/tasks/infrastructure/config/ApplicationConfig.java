package com.barmont.tasks.infrastructure.config;

import com.barmont.tasks.application.services.TaskService;
import com.barmont.tasks.application.usecases.CreateTaskUseCaseImpl;
import com.barmont.tasks.application.usecases.DeleteTaskUseCaseImpl;
import com.barmont.tasks.application.usecases.GetAdditionalTaskInfoUseCaseImpl;
import com.barmont.tasks.application.usecases.RetrieveTaskUseCaseImpl;
import com.barmont.tasks.application.usecases.UpdateTaskUseCaseImpl;
import com.barmont.tasks.domain.ports.in.GetAdditionalTaskInfoUseCase;
import com.barmont.tasks.domain.ports.out.ExternalServicePort;
import com.barmont.tasks.domain.ports.out.TaskRepositoryPort;
import com.barmont.tasks.infrastructure.adapters.ExternalServiceAdapter;
import com.barmont.tasks.infrastructure.repositories.JpaTaskRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public TaskService taskService(TaskRepositoryPort taskRepositoryPort, GetAdditionalTaskInfoUseCase getAdditionalTaskInfoUseCase) {
        return new TaskService(
                new CreateTaskUseCaseImpl(taskRepositoryPort),
                new RetrieveTaskUseCaseImpl(taskRepositoryPort),
                new UpdateTaskUseCaseImpl(taskRepositoryPort),
                new DeleteTaskUseCaseImpl(taskRepositoryPort),
                getAdditionalTaskInfoUseCase
        );
    }

    @Bean
    public TaskRepositoryPort taskRepositoryPort(JpaTaskRepositoryAdapter jpaTaskRepositoryAdapter) {
        return jpaTaskRepositoryAdapter;
    }

    @Bean
    public GetAdditionalTaskInfoUseCase getAdditionalTaskInfoUseCase(ExternalServicePort externalServicePort) {
        return new GetAdditionalTaskInfoUseCaseImpl(externalServicePort);
    }

    @Bean
    public ExternalServicePort externalServicePort() {
        return new ExternalServiceAdapter();
    }



}
