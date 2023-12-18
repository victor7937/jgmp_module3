package com.epam.victor.repository;


import com.epam.victor.model.TaskMongo;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("mongo")
//@RepositoryRestResource(collectionResourceRel = "tasks", path = "tasks")
public interface TaskRepositoryMongo extends MongoRepository<TaskMongo, String> {
    List<TaskMongo> findAllByDeadlineBefore(Instant dateTime);
    List<TaskMongo> findAllByCategory(String category);
    List<TaskMongo> findAllByDescriptionContainsIgnoreCase(String key);

    @Query("{'subTasks.name':{'$regex' : '?0', '$options' : 'i'}}")
    List<TaskMongo> findAllBySubTaskNameContains(String key);

    @Query("{'subTasks._id':ObjectId('?0')}")
    Optional<TaskMongo> getTaskBySubtaskId(String subTaskId);

}
