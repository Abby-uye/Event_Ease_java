package com.beautyclan.eventease.data.repositories;

import com.beautyclan.eventease.data.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface EventRepository extends JpaRepository<Event,Long> {
    Event findByNameEqualsIgnoreCaseAndVenueEqualsIgnoreCaseAndDateEquals(String name, String venue, LocalDate date);
}
