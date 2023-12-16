package com.epam.victor.service.postgres;

import com.epam.victor.model.SubTaskPostgres;
import com.epam.victor.model.TaskPostgres;
import com.epam.victor.model.dto.SubTaskInputDto;
import com.epam.victor.model.dto.TaskDto;
import com.epam.victor.model.dto.TaskInputDto;
import com.epam.victor.repository.SubTaskRepositoryPostgres;
import com.epam.victor.repository.TaskRepositoryPostgres;
import com.epam.victor.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import static com.epam.victor.service.util.PostgresMapperUtil.fromPostgres;
import static com.epam.victor.service.util.PostgresMapperUtil.toPostgres;

@Service
@Profile("postgres")
public class TaskServicePostgres implements TaskService {

    private final TaskRepositoryPostgres taskRepository;

    private final SubTaskRepositoryPostgres subTaskRepository;

    @Autowired
    public TaskServicePostgres(TaskRepositoryPostgres taskRepository, SubTaskRepositoryPostgres subTaskRepository) {
        this.taskRepository = taskRepository;
        this.subTaskRepository = subTaskRepository;
    }

    @Override
    @Transactional
    public TaskDto create(TaskInputDto inputTask, List<SubTaskInputDto> inputSubTasks) {
        TaskPostgres task = toPostgres(inputTask);
        List<SubTaskPostgres> subTasks = toPostgres(inputSubTasks);
        task.setDateOfCreation(Instant.now());
        subTasks.forEach(task::addSubtask);
        return fromPostgres(taskRepository.save(task));
    }



    @Override
    public List<TaskDto> getAll() {
        return null;
    }

    @Override
    public List<TaskDto> getAllOverdue() {
        return null;
    }

    @Override
    public List<TaskDto> findTaskByDescriptionKey(String key) {
        return null;
    }

    @Override
    public List<TaskDto> getAllWithCategory(String category) {
        return null;
    }

    @Override
    public void deleteTask(String taskId) {

    }

    @Override
    public void updateTask(TaskDto taskDto) {

    }
}
