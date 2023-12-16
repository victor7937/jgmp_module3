package com.epam.victor.repository;

import com.epam.victor.model.TaskPostgres;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("postgres")
public interface TaskRepositoryPostgres extends JpaRepository<TaskPostgres, Integer> {
}
