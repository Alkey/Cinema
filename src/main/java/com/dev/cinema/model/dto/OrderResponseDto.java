package com.dev.cinema.model.dto;

import java.util.List;
import lombok.Data;

@Data
public class OrderResponseDto {
    private Long orderId;
    private String orderDate;
    private List<TicketResponseDto> tickets;
}
