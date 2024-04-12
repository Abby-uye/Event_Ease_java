package com.beautyclan.eventease.dtos;

import com.beautyclan.eventease.data.models.Ticket;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Setter
@Getter
@ToString
public class TicketReservationResponse {
    private List<Ticket> tickets;
}
