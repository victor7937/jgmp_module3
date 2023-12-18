package com.epam.victor.repository;

import com.epam.victor.model.SubTaskPostgres;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("postgres")
public interface SubTaskRepositoryPostgres extends JpaRepository<SubTaskPostgres, Integer> {
    List<SubTaskPostgres> findSubTaskPostgresByTaskPostgres_Category(String category);

    void deleteAllByTaskPostgres_Id(Integer id);

    List<SubTaskPostgres> findAllByNameContainsIgnoreCase(String name);
}
