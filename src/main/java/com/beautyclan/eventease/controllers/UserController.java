package com.beautyclan.eventease.controllers;

import com.beautyclan.eventease.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/event_ease")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
private UserService userService;
@PostMapping("/register")
    public

}

