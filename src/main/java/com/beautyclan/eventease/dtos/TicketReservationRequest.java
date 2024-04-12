package com.beautyclan.eventease.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
public class TicketReservationRequest {
    @NotBlank(message = "Required field")
    private String eventName;
    @NotBlank(message = "Required field")
    private String eventVenue;
    @NotNull(message = "Required field")
    private Integer pieces;
    @NotNull(message = "Required field")
    private LocalDate eventDate;
    @NotBlank(message = "Required field")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+[a-zA-Z]{2,}$", message = "Please enter a valid email address.")
    private String userEmail;
}
