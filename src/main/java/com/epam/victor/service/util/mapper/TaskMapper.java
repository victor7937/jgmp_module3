package com.epam.victor.service.util.mapper;

import com.epam.victor.model.dto.SubTaskDto;
import com.epam.victor.model.dto.SubTaskInputDto;
import com.epam.victor.model.dto.TaskDto;
import com.epam.victor.model.dto.TaskInputDto;

import java.util.List;

public interface TaskMapper <T, S> {

    T toTaskFromInputDto(TaskInputDto taskInputDto);

    T toTaskFromDto(TaskDto taskInputDto);

    List<S> toSubtaskListFromInputDtoList(List<SubTaskInputDto> subTaskInputDtoList);

    S toSubTaskFromDto(SubTaskDto inputTask);

    S toSubTaskFromInputDto(SubTaskInputDto inputTask);

    List<S> toSubTaskListFromDtoList(List<SubTaskDto> taskDtoList);

    List<SubTaskDto> fromSubTaskListToSubTaskDtoList(List<S> subTaskList);

    TaskDto fromTaskToTaskDto(T task);

    SubTaskDto subTaskDtoFromSubTask(S subTask);

    List<TaskDto> fromTaskListToTaskDtoList(List<T> taskList);

}
