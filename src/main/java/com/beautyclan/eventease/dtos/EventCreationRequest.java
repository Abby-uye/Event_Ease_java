package com.beautyclan.eventease.dtos;

import com.beautyclan.eventease.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
public class EventCreationRequest {
    @NotBlank(message = "Required field")
    private String name;
    @NotBlank(message = "Required field")
    private String venue;
    @NotNull(message = "Required field")
    private LocalDate date;
    @NotNull(message = "Required field")
    private Integer attendeesCount;
    @NotBlank(message = "Required field")
    private String description;
    @NotBlank(message = "Required field")
    private Category category;
}
