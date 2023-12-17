package com.epam.victor.model.dto;

import com.epam.victor.model.currency.CurrencyPair;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskInputDto {
    private String name;

    private String description;

    private String category;

    private Instant deadline;

    private CurrencyPair currencyPair;
}
