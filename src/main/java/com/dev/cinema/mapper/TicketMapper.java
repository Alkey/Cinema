package com.dev.cinema.mapper;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.TicketResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    public TicketResponseDto getResponseDtoFromTicket(Ticket ticket) {
        TicketResponseDto dto = new TicketResponseDto();
        MovieSession session = ticket.getMovieSession();;
        dto.setId(ticket.getId());
        dto.setMovieId(session.getMovie().getId());
        dto.setMovieTitle(session.getMovie().getTitle());
        dto.setCinemaHallId(session.getCinemaHall().getId());
        dto.setCinemaHallDescription(session.getCinemaHall().getDescription());
        dto.setShowTime(session.getShowTime());
        return dto;
    }
}
