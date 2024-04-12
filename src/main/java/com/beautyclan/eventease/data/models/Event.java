package com.beautyclan.eventease.data.models;

import com.beautyclan.eventease.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private LocalDate date;

    private Integer attendeesCount;

    private String description;
    private String venue;

    private Category category;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Ticket> tickets;
}
