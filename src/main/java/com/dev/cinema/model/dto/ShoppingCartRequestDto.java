package com.dev.cinema.model.dto;

import lombok.Data;

@Data
public class ShoppingCartRequestDto {
    private Long movieSessionId;
    private Long userId;
}
