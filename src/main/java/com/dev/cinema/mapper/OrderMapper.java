package com.dev.cinema.mapper;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.dto.OrderResponseDto;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final TicketMapper mapper;

    @Autowired
    public OrderMapper(TicketMapper mapper) {
        this.mapper = mapper;
    }

    public OrderResponseDto getResponseDtoFromOrder(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setOrderId(order.getId());
        dto.setOrderDate(order.getOrderDate().toString());
        dto.setTickets(order.getTickets()
                .stream()
                .map(mapper::getResponseDtoFromTicket)
                .collect(Collectors.toList()));
        return dto;
    }
}
