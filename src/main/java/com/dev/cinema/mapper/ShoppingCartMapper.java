package com.dev.cinema.mapper;

import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.dto.ShoppingCartResponseDto;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShoppingCartMapper {
    private final TicketMapper mapper;

    public ShoppingCartResponseDto getResponseDtoFromShoppingCart(ShoppingCart cart) {
        ShoppingCartResponseDto dto = new ShoppingCartResponseDto();
        dto.setId(cart.getId());
        dto.setTickets(cart.getTickets()
                .stream()
                .map(mapper::getResponseDtoFromTicket)
                .collect(Collectors.toList()));
        return dto;
    }
}
