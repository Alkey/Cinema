package com.dev.cinema.model.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CinemaHallRequestDto {
    @NotNull
    private Integer capacity;
    @NotNull
    private String description;
}
