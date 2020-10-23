package com.dev.cinema.model.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MovieSessionResponseDto {
    private Long movieId;
    private String movieTitle;
    private Long cinemaHallId;
    private String cinemaHallDescription;
    private LocalDateTime showTime;
}
