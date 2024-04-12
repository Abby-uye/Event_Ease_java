package com.beautyclan.eventease.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
public class CancelReservationRequest {
private String eventName;
private String eventVenue;
private LocalDate date;
private String userEmail;
}
