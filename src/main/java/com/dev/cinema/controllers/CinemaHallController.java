package com.dev.cinema.controllers;

import com.dev.cinema.mapper.CinemaHallMapper;
import com.dev.cinema.model.dto.CinemaHallRequestDto;
import com.dev.cinema.model.dto.CinemaHallResponseDto;
import com.dev.cinema.service.CinemaHallService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
@RequiredArgsConstructor
public class CinemaHallController {
    private final CinemaHallService service;
    private final CinemaHallMapper mapper;

    @PostMapping
    public void addCinemaHall(@RequestBody @Valid CinemaHallRequestDto dto) {
        service.add(mapper.getCinemaHallFromRequestDto(dto));
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAllCinemaHalls() {
        return service.getAll()
                .stream()
                .map(mapper::getResponseDtoFromCinemaHall)
                .collect(Collectors.toList());
    }
}
