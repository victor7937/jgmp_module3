package com.epam.victor.service.mongo;

import com.epam.victor.model.SubTaskMongo;
import com.epam.victor.model.TaskMongo;
import com.epam.victor.model.dto.SubTaskDto;
import com.epam.victor.model.dto.SubTaskInputDto;
import com.epam.victor.repository.TaskRepositoryMongo;
import com.epam.victor.service.SubTaskService;
import com.epam.victor.service.exception.TaskNotFoundException;
import com.epam.victor.service.util.mapper.TaskMapper;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("mongo")
public class SubTaskServiceMongo implements SubTaskService {

    private final TaskRepositoryMongo taskRepository;

    private final TaskMapper<TaskMongo, SubTaskMongo> taskMapper;

    @Autowired
    public SubTaskServiceMongo(TaskRepositoryMongo taskRepositoryMongo, TaskMapper<TaskMongo, SubTaskMongo> taskMapper) {
        this.taskRepository = taskRepositoryMongo;
        this.taskMapper = taskMapper;
    }

    public List<SubTaskDto> getAllByTaskCategory(String category) {

        return taskMapper.fromSubTaskListToSubTaskDtoList(taskRepository.findAllByCategory(category)
                .stream()
                .flatMap(taskMongo -> taskMongo.getSubTasks().stream())
                .toList());
    }

    public void updateSubtaskListOfATask(List<SubTaskDto> subTasks, String taskId){
        TaskMongo task = findById(taskId);
        task.setSubTasks(taskMapper.toSubTaskListFromDtoList(subTasks));
        taskRepository.save(task);
    }

    public void create(SubTaskInputDto subTaskInput, String taskId) {
        TaskMongo task = findById(taskId);
        SubTaskMongo subTaskMongo = taskMapper.toSubTaskFromInputDto(subTaskInput);
        subTaskMongo.setId(new ObjectId().toString());
        task.getSubTasks().add(subTaskMongo);
        taskRepository.save(task);
    }

    public void deleteAllSubtasksOfATask(String taskId){
        TaskMongo task = findById(taskId);
        task.setSubTasks(new ArrayList<>());
        taskRepository.save(task);
    }

    public void deleteById(String subTaskId){
        TaskMongo task = taskRepository.getTaskBySubtaskId(subTaskId).orElseThrow(
                () -> new TaskNotFoundException("SubTask " + subTaskId + "can't be found")
        );
        List<SubTaskMongo> subTaskMongos = task.getSubTasks()
                .stream()
                .filter(st -> !st.getId().equals(subTaskId))
                .toList();
        task.setSubTasks(subTaskMongos);
        taskRepository.save(task);
    }

    public List<SubTaskDto> getAllByNameContains(String key){
        return taskMapper.fromSubTaskListToSubTaskDtoList(
                taskRepository.findAllBySubTaskNameContains(key).stream()
                .flatMap(taskMongo -> taskMongo.getSubTasks().stream())
                .filter(subTaskMongo -> subTaskMongo.getName().toLowerCase().contains(key.toLowerCase()))
                .toList()
        );
    }


    private TaskMongo findById(String taskId){
        return taskRepository.findById(taskId).orElseThrow(
                () -> new TaskNotFoundException("Task " + taskId + "can't be found")
        );
    }
}
