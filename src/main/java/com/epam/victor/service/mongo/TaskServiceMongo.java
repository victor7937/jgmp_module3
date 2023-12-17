package com.epam.victor.service.mongo;

import com.epam.victor.model.currency.CurrencyPair;
import com.epam.victor.model.SubTaskMongo;
import com.epam.victor.model.TaskMongo;
import com.epam.victor.model.dto.SubTaskInputDto;
import com.epam.victor.model.dto.TaskDto;
import com.epam.victor.model.dto.TaskInputDto;
import com.epam.victor.repository.TaskRepositoryMongo;
import com.epam.victor.service.TaskService;
import com.epam.victor.service.util.mapper.TaskMapper;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Profile("mongo")
public class TaskServiceMongo implements TaskService {

    private final TaskRepositoryMongo taskRepositoryMongo;

    private final TaskMapper<TaskMongo, SubTaskMongo> taskMapper;


    @Autowired
    public TaskServiceMongo(TaskRepositoryMongo taskRepositoryMongo, TaskMapper<TaskMongo, SubTaskMongo> taskMapper) {
        this.taskRepositoryMongo = taskRepositoryMongo;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskDto create(TaskInputDto inputTask, List<SubTaskInputDto> inputSubTasks) {
        TaskMongo task = taskMapper.toTaskFromInputDto(inputTask);
        List<SubTaskMongo> subTasks = taskMapper.toSubtaskListFromInputDtoList(inputSubTasks);
        subTasks.forEach(st -> st.setId(new ObjectId().toString()));
        task.setSubTasks(subTasks);
        task.setDateOfCreation(Instant.now());
        task.setCurrencyPair(CurrencyPair.of("USD/GEL"));
        return taskMapper.fromTaskToTaskDto(taskRepositoryMongo.save(task));
    }

    public List<TaskDto> getAll() {
        return taskMapper.fromTaskListToTaskDtoList(taskRepositoryMongo.findAll());
    }

    public List<TaskDto> getAllOverdue() {
        return taskMapper.fromTaskListToTaskDtoList(taskRepositoryMongo.findAllByDeadlineBefore(LocalDateTime.now()));
    }

    public List<TaskDto> findTaskByDescriptionKey(String key){
        return taskMapper.fromTaskListToTaskDtoList(taskRepositoryMongo.findAllByDescriptionContainsIgnoreCase(key));
    }

    public List<TaskDto> getAllWithCategory(String category) {
        return taskMapper.fromTaskListToTaskDtoList(taskRepositoryMongo.findAllByCategory(category));
    }

    public void deleteTask(String taskId) {
        taskRepositoryMongo.deleteById(taskId);
    }

    public void updateTask(TaskDto task) {
        taskRepositoryMongo.save(taskMapper.toTaskFromDto(task));
    }

}
