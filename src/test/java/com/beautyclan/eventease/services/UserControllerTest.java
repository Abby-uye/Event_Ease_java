package com.beautyclan.eventease.services;

import com.beautyclan.eventease.controllers.UserController;
import com.beautyclan.eventease.data.models.Ticket;
import com.beautyclan.eventease.dtos.*;
import com.beautyclan.eventease.enums.Category;
import com.beautyclan.eventease.exceptions.AlreadyExistException;
import com.beautyclan.eventease.exceptions.BeyondLimitException;
import com.beautyclan.eventease.exceptions.EventException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Test
    public void testRegisterUserSuccessfully() throws Exception, AlreadyExistException {
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setEmail("Uyai@gmail.com");
        request.setName("Uyai Abby");
        request.setPassword("Validpas123**");
        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setMessage("Registered successfully");

        when(userService.register(any(UserRegistrationRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/event_ease/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"email\": \"Uyai@gmail.com\", \"password\": \"Validpas123**\" ,\"name\": \"Uyai Abby\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Registered successfully"));


    }
@Test
public void testThatCanCreateEventSuccessfully() throws EventException, Exception {
    EventCreationRequest request = new EventCreationRequest();
    request.setName("birthday");
    request.setVenue("surulere");
    request.setAttendeesCount(24);
    request.setDate(LocalDate.of(2024, 6, 7));
    request.setCategory(Category.CONCERT); // Set the enum directly
    request.setDescription("Turning a new age");

    EventCreationResponse response = new EventCreationResponse();
    response.setDate(LocalDate.of(2024, 6, 7));
    response.setAttendeesCount(24);
    response.setName("birthday");
    response.setCategory(Category.CONCERT); // Set the enum directly
    response.setDescription("Turning a new age");

    when(userService.create(any(EventCreationRequest.class))).thenReturn(response);

    mockMvc.perform(post("/api/v1/event_ease/user/create_event")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"name\": \"birthday\",\"venue\": \"surulere\",\"attendeesCount\": 24,\"date\": \"2024-06-07\",\"category\": \"CONCERT\",\"description\": \"Turning a new age\"}"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
}
@Test
    public void testThatReserveTicketSuccessfully() throws EventException, BeyondLimitException, Exception {
        TicketReservationRequest request = new TicketReservationRequest();
        request.setUserEmail("Uyai@gmail.com\"");
        request.setEventName("house opening");
        request.setEventVenue("surulere");
        request.setEventDate(LocalDate.of(2024, 5, 18));
        request.setPieces(1);

    Ticket ticket = new Ticket();
    ticket.setEventName(request.getEventName());
    ticket.setEventVenue(request.getEventVenue());
    ticket.setEventDate(request.getEventDate());
    ticket.setId(1L);

    List<Ticket> tickets = new ArrayList<>();
    tickets.add(ticket);

        TicketReservationResponse response = new TicketReservationResponse();
        response.setTickets(tickets);

        when(userService.reserveTicket(any(TicketReservationRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/event_ease/user/reserve_ticket")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \" house opening\", \"venue\": \"surulere\",\"date\":\"2024,5,18\",\"pieces\": \"1\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
}


}
