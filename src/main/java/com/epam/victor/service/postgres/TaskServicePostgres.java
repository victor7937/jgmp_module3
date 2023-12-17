package com.epam.victor.service.postgres;

import com.epam.victor.model.SubTaskPostgres;
import com.epam.victor.model.TaskPostgres;
import com.epam.victor.model.currency.CurrencyPair;
import com.epam.victor.model.dto.SubTaskInputDto;
import com.epam.victor.model.dto.TaskDto;
import com.epam.victor.model.dto.TaskInputDto;
import com.epam.victor.repository.SubTaskRepositoryPostgres;
import com.epam.victor.repository.TaskRepositoryPostgres;
import com.epam.victor.service.TaskService;
import com.epam.victor.service.util.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@Profile("postgres")
public class TaskServicePostgres implements TaskService {

    private final TaskRepositoryPostgres taskRepository;

    private final SubTaskRepositoryPostgres subTaskRepository;

    private final TaskMapper<TaskPostgres, SubTaskPostgres> taskMapper;

    @Autowired
    public TaskServicePostgres(TaskRepositoryPostgres taskRepository,
                               SubTaskRepositoryPostgres subTaskRepository, TaskMapper<TaskPostgres,
                               SubTaskPostgres> taskMapper) {
        this.taskRepository = taskRepository;
        this.subTaskRepository = subTaskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    @Transactional
    public TaskDto create(TaskInputDto inputTask, List<SubTaskInputDto> inputSubTasks) {
        TaskPostgres task = taskMapper.toTaskFromInputDto(inputTask);
        List<SubTaskPostgres> subTasks = taskMapper.toSubtaskListFromInputDtoList(inputSubTasks);
        task.setDateOfCreation(Instant.now());
        task.setCurrencyPair(CurrencyPair.of("USD/GEL"));
        subTasks.forEach(task::addSubtask);
        return taskMapper.fromTaskToTaskDto(taskRepository.save(task));
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
