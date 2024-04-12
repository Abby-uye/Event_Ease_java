package com.beautyclan.eventease.services;

import com.beautyclan.eventease.dtos.*;
import com.beautyclan.eventease.enums.Category;
import com.beautyclan.eventease.exceptions.AlreadyExistException;
import com.beautyclan.eventease.exceptions.BeyondLimitException;
import com.beautyclan.eventease.exceptions.EventException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Slf4j
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void TestThatUserCanRegister() throws AlreadyExistException {
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setName("Abigail Godwin");
        request.setEmail("princessabigail@gmail.com");
        request.setPassword("Valid12**");
        UserRegistrationResponse response = userService.register(request);
    }
    @Test
    public void testThatICanCreateAnEvent() throws EventException {
        EventCreationRequest request = new EventCreationRequest();
        request.setName("graduation");
        request.setCategory(Category.valueOf("CONCERT"));
        request.setDescription("parry all day");
        request.setDate(LocalDate.of(2024,7,17));
        request.setAttendeesCount(70);
        request.setVenue("yaba");
        EventCreationResponse response = userService.create(request);
        log.info("creat event response {}",response);
        assertNotNull(response);

    }
@Test
    public void tesThatIfAttendeesCountIsGreaterThan1000ExceptionIsThrown() throws EventException {
    EventCreationRequest request = new EventCreationRequest();
    request.setName("Wedding");
    request.setCategory(Category.valueOf("CONCERT"));
    request.setDescription("getting married");
    request.setDate(LocalDate.of(2024,7,17));
    request.setAttendeesCount(1005);
    request.setVenue("yaba");
    assertThrows(EventException.class,()-> userService.create(request));
}

    @Test
    public void tesThatWhenNameFieldCharacterIsGreaterThan100ExceptionIsThrown() throws EventException {
        EventCreationRequest request = new EventCreationRequest();
        request.setName("W".repeat(120));
        request.setCategory(Category.valueOf("CONCERT"));
        request.setDescription("getting married");
        request.setDate(LocalDate.of(2024,7,17));
        request.setVenue("ikoyi");
        request.setAttendeesCount(100);
        assertThrows(EventException.class,()-> userService.create(request));
    }
@Test
    public void tesThatICanReserveEventTicket() throws EventException, BeyondLimitException {
        TicketReservationRequest request = new TicketReservationRequest();
        request.setEventName("graduation");
        request.setEventVenue("yaba");
        request.setPieces(2);
        request.setUserEmail("princessabigail@gmail.com");
        request.setEventDate(LocalDate.of(2024,7,17));
        TicketReservationResponse response = userService.reserveTicket(request);
        log.info("Reserved ticket{}",response);
        assertNotNull(response);


}
@Test
    public void testThatICanViewReservedEvent() throws EventException {
        GetUserEventsRequest request = new GetUserEventsRequest();
        request.setUserEmail("princessabigail@gmail.com");
        GetUserEventsResponse response = userService.getAllEvents(request);
        log.info("{}",response);
        assertNotNull(response);


}
@Test
    public void testThatICanCancelEventBooking() throws EventException {
        CancelReservationRequest request = new CancelReservationRequest();
        request.setEventVenue("yaba");
        request.setEventName("graduation");
        request.setUserEmail("princessabigail@gmail.com");
        request.setDate(LocalDate.of(2024,7,17));
        CancelReservationResponse response = userService.cancelReservation(request);
        log.info("{}",response);
        assertNotNull(response);

}

}
