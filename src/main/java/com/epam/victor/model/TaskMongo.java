package com.epam.victor.model;

import com.epam.victor.model.currency.CurrencyPair;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
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

    private Instant deadline;

    private CurrencyPair currencyPair;

    private List<SubTaskMongo> subTasks;



}
