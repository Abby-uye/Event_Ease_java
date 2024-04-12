package com.beautyclan.eventease.services;

import com.beautyclan.eventease.dtos.*;
import com.beautyclan.eventease.exceptions.AlreadyExistException;
import com.beautyclan.eventease.exceptions.BeyondLimitException;
import com.beautyclan.eventease.exceptions.EventException;

public interface UserService {
    UserRegistrationResponse register(UserRegistrationRequest request) throws AlreadyExistException;


    EventCreationResponse create(EventCreationRequest request) throws EventException;

    TicketReservationResponse reserveTicket(TicketReservationRequest request) throws EventException, BeyondLimitException;

    GetUserEventsResponse getAllEvents(GetUserEventsRequest request) throws EventException;
}
