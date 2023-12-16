package com.epam.victor.model.dto;

import com.epam.victor.model.SubTaskMongo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private String id;

    private String name;

    private String description;

    private String category;

    private Instant dateOfCreation;

    private Instant deadline;

    private List<SubTaskMongo> subTasks;
}
