package com.epam.victor.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;


@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubTaskMongo {

    @Id
    String id;

    String name;

    String description;

    public SubTaskMongo(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
