package com.beautyclan.eventease.data.repositories;

import com.beautyclan.eventease.data.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
