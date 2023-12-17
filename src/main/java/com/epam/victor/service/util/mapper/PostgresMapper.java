package com.epam.victor.service.util.mapper;

import com.epam.victor.model.SubTaskPostgres;
import com.epam.victor.model.TaskPostgres;
import com.epam.victor.model.dto.SubTaskDto;
import com.epam.victor.model.dto.SubTaskInputDto;
import com.epam.victor.model.dto.TaskDto;
import com.epam.victor.model.dto.TaskInputDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class PostgresMapper implements TaskMapper<TaskPostgres, SubTaskPostgres> {

    @Override
    public TaskPostgres toTaskFromInputDto(TaskInputDto taskInputDto) {
        return MapperUtil.map(taskInputDto, TaskPostgres.class);
    }

    @Override
    public TaskPostgres toTaskFromDto(TaskDto taskDto) {
        TaskPostgres task = MapperUtil.map(taskDto, TaskPostgres.class);
        task.setId(Integer.parseInt(taskDto.getId()));
        return task;
    }

    @Override
    public List<SubTaskPostgres> toSubtaskListFromInputDtoList(List<SubTaskInputDto> subTaskInputDtoList) {
        return MapperUtil.mapAll(subTaskInputDtoList, SubTaskPostgres.class);
    }

    @Override
    public SubTaskPostgres toSubTaskFromDto(SubTaskDto inputTask) {
        SubTaskPostgres subTask = MapperUtil.map(inputTask, SubTaskPostgres.class);
        subTask.setId(Integer.parseInt(inputTask.getId()));
        return subTask;
    }

    @Override
    public SubTaskPostgres toSubTaskFromInputDto(SubTaskInputDto inputTask) {
        return MapperUtil.map(inputTask, SubTaskPostgres.class);
    }

    @Override
    public List<SubTaskPostgres> toSubTaskListFromDtoList(List<SubTaskDto> taskDtoList) {
        return taskDtoList.stream()
                .map(this::toSubTaskFromDto)
                .toList();
    }

    @Override
    public List<SubTaskDto> fromSubTaskListToSubTaskDtoList(List<SubTaskPostgres> subTaskList) {
        return subTaskList.stream()
                .map(this::subTaskDtoFromSubTask)
                .toList();
    }

    @Override
    public TaskDto fromTaskToTaskDto(TaskPostgres task) {
        TaskDto dto = MapperUtil.map(task, TaskDto.class);
        dto.setId(task.getId().toString());
        dto.setSubTasks(fromSubTaskListToSubTaskDtoList(task.getSubTasks()));
        return dto;
    }

    @Override
    public SubTaskDto subTaskDtoFromSubTask(SubTaskPostgres subTask) {
        SubTaskDto dto = MapperUtil.map(subTask, SubTaskDto.class);
        dto.setId(subTask.getId().toString());
        return dto;
    }

    @Override
    public List<TaskDto> fromTaskListToTaskDtoList(List<TaskPostgres> taskList) {
        return taskList.stream()
                .map(this::fromTaskToTaskDto)
                .toList();
    }
}
