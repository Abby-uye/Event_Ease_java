package com.beautyclan.eventease.controllers;

import com.beautyclan.eventease.dtos.EventCreationRequest;
import com.beautyclan.eventease.dtos.TicketReservationRequest;
import com.beautyclan.eventease.dtos.UserRegistrationRequest;
import com.beautyclan.eventease.exceptions.AlreadyExistException;
import com.beautyclan.eventease.exceptions.BeyondLimitException;
import com.beautyclan.eventease.exceptions.EventException;
import com.beautyclan.eventease.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/event_ease/")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
private UserService userService;
@PostMapping("user/register")
    public ResponseEntity<?> register (@RequestBody UserRegistrationRequest request) throws AlreadyExistException {
    return ResponseEntity.ok(userService.register(request));

}

@PostMapping("user/create_event")
    public ResponseEntity<?> createEvent(@RequestBody EventCreationRequest request) throws EventException {
    return ResponseEntity.ok(userService.create(request));
}

@PostMapping("user/reserve_ticket")
    public ResponseEntity<?> reserveTicket(@RequestBody TicketReservationRequest request) throws EventException, BeyondLimitException {
    return ResponseEntity.ok(userService.reserveTicket(request));
}

}

