package com.beautyclan.eventease.services;

import com.beautyclan.eventease.data.models.Event;
import com.beautyclan.eventease.data.models.Ticket;
import com.beautyclan.eventease.data.models.User;
import com.beautyclan.eventease.data.repositories.EventRepository;
import com.beautyclan.eventease.data.repositories.TicketRepository;
import com.beautyclan.eventease.data.repositories.UserRepository;
import com.beautyclan.eventease.dtos.*;
import com.beautyclan.eventease.exceptions.AlreadyExistException;
import com.beautyclan.eventease.exceptions.BeyondLimitException;
import com.beautyclan.eventease.exceptions.EventException;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor

public class EventEaseUserService implements UserService{
    private EventRepository eventRepository;
    private ModelMapper modelMapper;
private TicketRepository ticketRepository;
    private UserRepository userRepository;


    @Override
    public UserRegistrationResponse register(UserRegistrationRequest request) throws AlreadyExistException {
        if (existingUser(request.getEmail())) throw new AlreadyExistException("A user with this details already exist try logging in instead");
        User newUser = modelMapper.map(request, User.class);
        userRepository.save(newUser);
        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setMessage("Registered successfully ");
        return response;
    }
    @Override
    public EventCreationResponse create(EventCreationRequest request) throws EventException {
        if(!validName(request.getName())) throw new EventException("name is too long");
        if(beyondLimit(request.getAttendeesCount())) throw new EventException("you've exceeded the attendees count limit");
        if(!withinLimit(request.getDescription())) throw new EventException("Description must be less 501 characters");
        Event event = modelMapper.map(request,Event.class);
        eventRepository.save(event);
        return modelMapper.map(request,EventCreationResponse.class);
    }

    @Override
    public TicketReservationResponse reserveTicket(TicketReservationRequest request) throws EventException, BeyondLimitException {
        Event foundEvent =findEventByCriteria(request.getEventName(),request.getEventVenue(),request.getEventDate());
        if(foundEvent == null)
            throw new EventException("Not a valid event please ensure that all entries are correct");
        if(beyondLimit(foundEvent.getAttendeesCount()))throw new BeyondLimitException("Attendees limit reached");
        if(beyondLimit(request.getPieces(),foundEvent.getTickets())) throw new BeyondLimitException("the event capacity cannot take up to this number of tickets please reduce tickets");
        User foundUser = findBy(request.getUserEmail());
        List<Ticket> tickets = createTicket(request);
       foundEvent.setTickets( addToEventTickets(foundEvent.getTickets(),tickets));
       foundUser.setTickets(tickets);
       foundUser.getEvents().add(foundEvent);
       eventRepository.save(foundEvent);
       userRepository.save(foundUser);
       TicketReservationResponse response = new TicketReservationResponse();
       response.setTickets(tickets);
        return response;
    }

    @Override
    public GetUserEventsResponse getAllEvents(GetUserEventsRequest request) throws EventException {
        User user = findBy(request.getUserEmail());
        List<Event> events = user.getEvents();
        if (events.isEmpty()) throw new EventException("you don't have any event reserved");
        GetUserEventsResponse response = new GetUserEventsResponse();
        response.setEvents(events);
        return response;
    }

    private boolean beyondLimit(Integer pieces, List<Ticket> tickets) {
       return tickets.size() + pieces > 1000;
    }

    private List<Ticket> addToEventTickets(List<Ticket> eventTickets, List<Ticket> createdTickets) {
        eventTickets.addAll(createdTickets);
        return eventTickets;
    }


    private List<Ticket> createTicket(TicketReservationRequest request) {
        return IntStream.range(0, request.getPieces())
                .mapToObj(i -> {
                    Ticket ticket = modelMapper.map(request, Ticket.class);
                    ticketRepository.save(ticket);
                    return ticket;
                })
                .collect(Collectors.toList());
    }

    private User findBy(String userEmail) throws EventException {
        return userRepository.findByEmail(userEmail).orElseThrow(() ->
            new EventException("Not a registered user please register"));

    }

    private Event findEventByCriteria( String eventName, String eventVenue,LocalDate eventDate) {
        return eventRepository.findByNameEqualsIgnoreCaseAndVenueEqualsIgnoreCaseAndDateEquals(eventName,eventVenue,eventDate);
    }

    private boolean beyondLimit(Integer attendeesCount) {
        return attendeesCount > 1000;
    }

    private boolean withinLimit(String description) {
        return description.length() <=500;
    }

    private boolean validName(String name) {
        return name.length()<=100;
    }

    private boolean existingUser(String email) {

        return userRepository.findByEmail(email).isPresent();

    }
}
