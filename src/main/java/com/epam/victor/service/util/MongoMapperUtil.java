package com.epam.victor.service.util;

import com.epam.victor.model.SubTaskMongo;
import com.epam.victor.model.TaskMongo;
import com.epam.victor.model.dto.SubTaskDto;
import com.epam.victor.model.dto.SubTaskInputDto;
import com.epam.victor.model.dto.TaskDto;
import com.epam.victor.model.dto.TaskInputDto;

import java.util.List;

public class MongoMapperUtil {
    public static TaskMongo toMongo(TaskInputDto inputTask){
        return MapperUtil.map(inputTask, TaskMongo.class);
    }


    public static List<SubTaskMongo> toMongo(List<SubTaskInputDto> taskMongoList){
        return MapperUtil.mapAll(taskMongoList, SubTaskMongo.class);
    }

    public static TaskMongo toMongo(TaskDto inputTask){
        return MapperUtil.map(inputTask, TaskMongo.class);
    }

    public static SubTaskMongo toMongoSubTask(SubTaskDto inputTask){
        return MapperUtil.map(inputTask, SubTaskMongo.class);
    }

    public static SubTaskMongo toMongoSubTask(SubTaskInputDto inputTask){
        return MapperUtil.map(inputTask, SubTaskMongo.class);
    }

    public static List<SubTaskMongo> toMongoSubTask(List<SubTaskDto> taskDtoList){
        return MapperUtil.mapAll(taskDtoList, SubTaskMongo.class);
    }

    public static List<SubTaskDto> fromMongoSubTask(List<SubTaskMongo> taskMongoList){
        return MapperUtil.mapAll(taskMongoList, SubTaskDto.class);
    }

    public static TaskDto fromMongo(TaskMongo taskMongo){
        return MapperUtil.map(taskMongo, TaskDto.class);
    }

    public static List<TaskDto> fromMongo(List<TaskMongo> taskMongoList){
        return MapperUtil.mapAll(taskMongoList, TaskDto.class);
    }
}
