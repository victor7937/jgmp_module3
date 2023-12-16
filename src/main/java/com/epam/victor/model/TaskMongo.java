package com.epam.victor.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "tasks")
@Data
@NoArgsConstructor
public class TaskMongo {

    @Id
    private String id;

    private String name;

    private String description;

    private String category;

    private Instant dateOfCreation;
    //todo Change to Instant
    private Instant deadline;

    private List<SubTaskMongo> subTasks;

    //todo add currency Base
    // no annotation

    public TaskMongo(String name,
                     String description,
                     String category,
                     Instant dateOfCreation,
                     Instant deadline,
                     List<SubTaskMongo> subTasks) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.dateOfCreation = dateOfCreation;
        this.deadline = deadline;
        this.subTasks = subTasks;
    }
}
