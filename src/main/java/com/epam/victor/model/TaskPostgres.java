package com.epam.victor.model;

import com.epam.victor.model.converter.CurrencyPairConverter;
import com.epam.victor.model.currency.CurrencyPair;
import com.epam.victor.repository.SubTaskRepositoryPostgres;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
public class TaskPostgres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "date_of_creation")
    private Instant dateOfCreation;

    @Column(name = "deadline")
    private Instant deadline;

    @Column(name="currency_pair")
    private CurrencyPair currencyPair;

    @OneToMany(mappedBy = "taskPostgres", cascade = CascadeType.PERSIST)
    private List<SubTaskPostgres> subTasks = new ArrayList<>();

    public void addSubtask(SubTaskPostgres subTaskPostgres){
        this.subTasks.add(subTaskPostgres);
        subTaskPostgres.setTaskPostgres(this);
    }
}
