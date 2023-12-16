package com.epam.victor.service;

import com.epam.victor.model.dto.SubTaskDto;
import com.epam.victor.model.dto.SubTaskInputDto;
import com.epam.victor.model.dto.TaskDto;
import com.epam.victor.model.dto.TaskInputDto;

import java.util.List;

public interface TaskService {
    TaskDto create(TaskInputDto inputTask, List<SubTaskInputDto> subTasks);

    List<TaskDto> getAll();

    List<TaskDto> getAllOverdue();

    List<TaskDto> findTaskByDescriptionKey(String key);

    List<TaskDto> getAllWithCategory(String category);

    void deleteTask(String taskId);

    void updateTask(TaskDto taskDto);

}
