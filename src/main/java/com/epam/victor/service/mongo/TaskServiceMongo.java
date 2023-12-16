package com.epam.victor.service.mongo;

import com.epam.victor.model.SubTaskMongo;
import com.epam.victor.model.TaskMongo;
import com.epam.victor.model.dto.SubTaskDto;
import com.epam.victor.model.dto.SubTaskInputDto;
import com.epam.victor.model.dto.TaskDto;
import com.epam.victor.model.dto.TaskInputDto;
import com.epam.victor.repository.TaskRepositoryMongo;
import com.epam.victor.service.TaskService;
import com.epam.victor.service.util.MapperUtil;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import static com.epam.victor.service.util.MongoMapperUtil.fromMongo;
import static com.epam.victor.service.util.MongoMapperUtil.toMongo;

@Service
@Profile("mongo")
public class TaskServiceMongo implements TaskService {

    private final TaskRepositoryMongo taskRepositoryMongo;


    @Autowired
    public TaskServiceMongo(TaskRepositoryMongo taskRepositoryMongo) {
        this.taskRepositoryMongo = taskRepositoryMongo;
    }

    @Override
    public TaskDto create(TaskInputDto inputTask, List<SubTaskInputDto> inputSubTasks) {
        TaskMongo task = toMongo(inputTask);
        List<SubTaskMongo> subTasks = toMongo(inputSubTasks);
        subTasks.forEach(st -> st.setId(new ObjectId().toString()));
        task.setSubTasks(subTasks);
        task.setDateOfCreation(Instant.now());
        return fromMongo(taskRepositoryMongo.save(task));
    }

    public List<TaskDto> getAll() {
        return fromMongo(taskRepositoryMongo.findAll());
    }

    public List<TaskDto> getAllOverdue() {
        return fromMongo(taskRepositoryMongo.findAllByDeadlineBefore(LocalDateTime.now()));
    }

    public List<TaskDto> findTaskByDescriptionKey(String key){
        return fromMongo(taskRepositoryMongo.findAllByDescriptionContainsIgnoreCase(key));
    }

    public List<TaskDto> getAllWithCategory(String category) {
        return fromMongo(taskRepositoryMongo.findAllByCategory(category));
    }

    public void deleteTask(String taskId) {
        taskRepositoryMongo.deleteById(taskId);
    }

    public void updateTask(TaskDto task) {
        taskRepositoryMongo.save(toMongo(task));
    }

}
