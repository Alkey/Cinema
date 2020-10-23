package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.UserRequestDto;
import com.dev.cinema.security.AuthenticationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private static final Logger logger = Logger.getLogger(AuthenticationController.class);
    private final AuthenticationService service;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.service = authenticationService;
    }

    @PostMapping("/registration")
    public void registration(@RequestBody UserRequestDto dto) {
        service.register(dto.getEmail(), dto.getPassword());
    }
}
