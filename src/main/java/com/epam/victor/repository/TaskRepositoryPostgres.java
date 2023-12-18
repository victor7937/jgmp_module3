package com.epam.victor.repository;

import com.epam.victor.model.TaskPostgres;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
@Profile("postgres")
public interface TaskRepositoryPostgres extends JpaRepository<TaskPostgres, Integer> {

    List<TaskPostgres> findAllByDeadlineBefore(Instant time);

    List<TaskPostgres> findAllByDescriptionContainsIgnoreCase(String key);

    List<TaskPostgres> findAllByCategory(String category);
}
