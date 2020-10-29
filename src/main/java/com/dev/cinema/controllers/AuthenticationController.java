package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.UserRegistrationDto;
import com.dev.cinema.security.AuthenticationService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService service;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.service = authenticationService;
    }

    @PostMapping("/registration")
    public void registration(@RequestBody @Valid UserRegistrationDto dto) {
        service.register(dto.getEmail(), dto.getPassword());
    }
}
