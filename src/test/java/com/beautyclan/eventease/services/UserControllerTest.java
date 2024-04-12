package com.beautyclan.eventease.services;

import com.beautyclan.eventease.controllers.UserController;
import com.beautyclan.eventease.dtos.UserRegistrationRequest;
import com.beautyclan.eventease.dtos.UserRegistrationResponse;
import com.beautyclan.eventease.exceptions.AlreadyExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"email\": \"Uyai@gmail.com\", \"password\": \"Validpas123**\" ,\"name\": \"Uyai Abby\"}"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$.message").value("Registered successfully"));


    }


}
