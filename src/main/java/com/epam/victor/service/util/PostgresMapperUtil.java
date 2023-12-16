package com.epam.victor.service.util;

import com.epam.victor.model.SubTaskMongo;
import com.epam.victor.model.SubTaskPostgres;
import com.epam.victor.model.TaskMongo;
import com.epam.victor.model.TaskPostgres;
import com.epam.victor.model.dto.SubTaskInputDto;
import com.epam.victor.model.dto.TaskDto;
import com.epam.victor.model.dto.TaskInputDto;

import java.util.List;

public class PostgresMapperUtil {

    public static TaskDto fromPostgres(TaskPostgres taskPostgres){
        return MapperUtil.map(taskPostgres, TaskDto.class);
    }

    public static List<TaskDto> fromPostgres(List<TaskPostgres> taskPostgresList){
        return MapperUtil.mapAll(taskPostgresList, TaskDto.class);
    }

    public static TaskPostgres toPostgres(TaskDto taskDto){
        return MapperUtil.map(taskDto, TaskPostgres.class);
    }

    public static TaskPostgres toPostgres(TaskInputDto taskInputDto){
        return MapperUtil.map(taskInputDto, TaskPostgres.class);
    }

    public static List<SubTaskPostgres> toPostgres(List<SubTaskInputDto> taskMongoList){
        return MapperUtil.mapAll(taskMongoList, SubTaskPostgres.class);
    }




}
