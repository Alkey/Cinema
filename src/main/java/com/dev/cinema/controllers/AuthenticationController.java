package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.UserRegistrationDto;
import com.dev.cinema.security.AuthenticationService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/registration")
    public void registration(@RequestBody @Valid UserRegistrationDto dto) {
        service.register(dto.getEmail(), dto.getPassword());
    }
}
