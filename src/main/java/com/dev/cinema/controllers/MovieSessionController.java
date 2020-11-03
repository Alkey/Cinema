package com.dev.cinema.controllers;

import com.dev.cinema.mapper.MovieSessionMapper;
import com.dev.cinema.model.dto.MovieSessionRequestDto;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MovieSessionController {
    private final MovieSessionService service;
    private final MovieSessionMapper mapper;

    @GetMapping("/movie-sessions/available")
    public List<MovieSessionResponseDto> getAvailable(
            @RequestParam Long movieId,
            @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date) {
        return service.findAvailableSessions(movieId, date).stream()
                .map(mapper::getResponseDtoFromMovieSession)
                .collect(Collectors.toList());
    }

    @PostMapping("/movie-sessions")
    public void addMovieSession(@RequestBody @Valid MovieSessionRequestDto dto) {
        service.add(mapper.getMovieSessionFromRequestDto(dto));
    }
}
