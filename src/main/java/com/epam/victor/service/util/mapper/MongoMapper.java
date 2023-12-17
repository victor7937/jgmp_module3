package com.epam.victor.service.util.mapper;

import com.epam.victor.model.SubTaskMongo;
import com.epam.victor.model.TaskMongo;
import com.epam.victor.model.dto.SubTaskDto;
import com.epam.victor.model.dto.SubTaskInputDto;
import com.epam.victor.model.dto.TaskDto;
import com.epam.victor.model.dto.TaskInputDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MongoMapper implements TaskMapper<TaskMongo, SubTaskMongo> {
    public TaskMongo toTaskFromInputDto(TaskInputDto inputTask){
        return MapperUtil.map(inputTask, TaskMongo.class);
    }


    public List<SubTaskMongo> toSubtaskListFromInputDtoList(List<SubTaskInputDto> taskMongoList){
        return MapperUtil.mapAll(taskMongoList, SubTaskMongo.class);
    }

    public TaskMongo toTaskFromDto(TaskDto inputTask){
        return MapperUtil.map(inputTask, TaskMongo.class);
    }

    public SubTaskMongo toSubTaskFromDto(SubTaskDto inputTask){
        return MapperUtil.map(inputTask, SubTaskMongo.class);
    }

    public SubTaskMongo toSubTaskFromInputDto(SubTaskInputDto inputTask){
        return MapperUtil.map(inputTask, SubTaskMongo.class);
    }

    public List<SubTaskMongo> toSubTaskListFromDtoList(List<SubTaskDto> taskDtoList){
        return MapperUtil.mapAll(taskDtoList, SubTaskMongo.class);
    }

    public List<SubTaskDto> fromSubTaskListToSubTaskDtoList(List<SubTaskMongo> taskMongoList){
        return MapperUtil.mapAll(taskMongoList, SubTaskDto.class);
    }

    public TaskDto fromTaskToTaskDto(TaskMongo taskMongo){
        return MapperUtil.map(taskMongo, TaskDto.class);
    }

    @Override
    public SubTaskDto subTaskDtoFromSubTask(SubTaskMongo subTask) {
        return MapperUtil.map(subTask, SubTaskDto.class);
    }

    public List<TaskDto> fromTaskListToTaskDtoList(List<TaskMongo> taskMongoList){
        return MapperUtil.mapAll(taskMongoList, TaskDto.class);
    }


}
