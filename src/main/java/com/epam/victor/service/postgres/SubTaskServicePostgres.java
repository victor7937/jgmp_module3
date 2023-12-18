package com.epam.victor.service.postgres;

import com.epam.victor.model.SubTaskPostgres;
import com.epam.victor.model.TaskPostgres;
import com.epam.victor.model.dto.SubTaskDto;
import com.epam.victor.model.dto.SubTaskInputDto;
import com.epam.victor.repository.SubTaskRepositoryPostgres;
import com.epam.victor.repository.TaskRepositoryPostgres;
import com.epam.victor.service.SubTaskService;
import com.epam.victor.service.exception.TaskNotFoundException;
import com.epam.victor.service.util.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("postgres")
public class SubTaskServicePostgres implements SubTaskService {

    private final SubTaskRepositoryPostgres subTaskRepository;

    private final TaskRepositoryPostgres taskRepository;

    private final TaskMapper<TaskPostgres, SubTaskPostgres> taskMapper;

    @Autowired
    public SubTaskServicePostgres(SubTaskRepositoryPostgres subTaskRepository,
                                  TaskRepositoryPostgres taskRepository,
                                  TaskMapper<TaskPostgres, SubTaskPostgres> taskMapper) {
        this.subTaskRepository = subTaskRepository;
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<SubTaskDto> getAllByTaskCategory(String category) {
        return taskMapper.fromSubTaskListToSubTaskDtoList(
                subTaskRepository.findSubTaskPostgresByTaskPostgres_Category(category));
    }

    @Override
    public void updateSubtaskListOfATask(List<SubTaskDto> subTasks, String taskId) {
        TaskPostgres task = getById(Integer.parseInt(taskId));
        task.getSubTasks().clear();
        taskMapper.toSubTaskListFromDtoList(subTasks).forEach(task::addSubtask);
        taskRepository.save(task);
    }

    @Override
    public void create(SubTaskInputDto subTask, String taskId) {
        TaskPostgres task = getById(Integer.parseInt(taskId));
        task.addSubtask(taskMapper.toSubTaskFromInputDto(subTask));
        taskRepository.save(task);
    }

    @Override
    public void deleteAllSubtasksOfATask(String taskId) {
        subTaskRepository.deleteAllByTaskPostgres_Id(Integer.parseInt(taskId));
    }

    @Override
    public void deleteById(String subTaskId) {
        subTaskRepository.deleteById(Integer.parseInt(subTaskId));
    }

    @Override
    public List<SubTaskDto> getAllByNameContains(String key) {
        return taskMapper.fromSubTaskListToSubTaskDtoList(
                subTaskRepository.findAllByNameContainsIgnoreCase(key));
    }

    private TaskPostgres getById(Integer id){
        return taskRepository.findById(id).orElseThrow(
                () -> new TaskNotFoundException("Task " + id + " cannot be found"));
    }
}
