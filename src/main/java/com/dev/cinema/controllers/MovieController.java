package com.dev.cinema.controllers;

import com.dev.cinema.mapper.MovieMapper;
import com.dev.cinema.model.dto.MovieRequestDto;
import com.dev.cinema.model.dto.MovieResponseDto;
import com.dev.cinema.service.MovieService;
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
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService service;
    private final MovieMapper mapper;

    @PostMapping
    public void addMovie(@RequestBody @Valid MovieRequestDto dto) {
        service.add(mapper.getMovieFromRequestDto(dto));
    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        return service.getAll().stream()
                .map(mapper::getResponseDtoFromMovie)
                .collect(Collectors.toList());
    }
}
