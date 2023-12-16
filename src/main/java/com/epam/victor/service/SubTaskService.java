package com.epam.victor.service;

import com.epam.victor.model.dto.SubTaskDto;
import com.epam.victor.model.dto.SubTaskInputDto;

import java.util.List;

public interface SubTaskService {

    List<SubTaskDto> getAllByTaskCategory(String category);

    void updateSubtaskListOfATask(List<SubTaskDto> subTasks, String taskId);

    void create(SubTaskInputDto subTask, String taskId);

    void deleteAllSubtasksOfATask(String taskId);

    void deleteById(String subTaskId);

    List<SubTaskDto> getAllByNameContains(String key);

}
