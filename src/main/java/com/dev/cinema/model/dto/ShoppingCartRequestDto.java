package com.dev.cinema.model.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ShoppingCartRequestDto {
    @NotNull
    private Long movieSessionId;
    @NotNull
    private Long userId;
}
