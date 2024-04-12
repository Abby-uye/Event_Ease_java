package com.beautyclan.eventease.dtos;

import com.beautyclan.eventease.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
public class EventCreationResponse {

    private String name;

    private LocalDate date;

    private Integer attendeesCount;

    private String description;

    private Category category;
}
