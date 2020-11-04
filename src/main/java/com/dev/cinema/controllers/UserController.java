package com.dev.cinema.controllers;

import com.dev.cinema.mapper.UserMapper;
import com.dev.cinema.model.dto.UserResponseDto;
import com.dev.cinema.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    @GetMapping("/by-email")
    public UserResponseDto getUserByEmail(@RequestParam String email) {
        return mapper.getResponseDtoFromUser(service.findByEmail(email).get());
    }
}
