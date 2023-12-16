package com.epam.victor.service.postgres;

import com.epam.victor.model.dto.SubTaskDto;
import com.epam.victor.model.dto.SubTaskInputDto;
import com.epam.victor.service.SubTaskService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("postgres")
public class SubTaskServicePostgres implements SubTaskService {
    @Override
    public List<SubTaskDto> getAllByTaskCategory(String category) {
        return null;
    }

    @Override
    public void updateSubtaskListOfATask(List<SubTaskDto> subTasks, String taskId) {

    }

    @Override
    public void create(SubTaskInputDto subTask, String taskId) {

    }

    @Override
    public void deleteAllSubtasksOfATask(String taskId) {

    }

    @Override
    public void deleteById(String subTaskId) {

    }

    @Override
    public List<SubTaskDto> getAllByNameContains(String key) {
        return null;
    }
}
