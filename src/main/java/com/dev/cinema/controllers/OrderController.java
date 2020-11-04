package com.dev.cinema.controllers;

import com.dev.cinema.mapper.OrderMapper;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.OrderResponseDto;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final OrderMapper mapper;
    private final ShoppingCartService shoppingCartService;

    @PostMapping("/complete")
    public void completeOrder(Authentication auth) {
        User user = userService.findByEmail(auth.getName()).get();
        orderService.completeOrder(shoppingCartService.getByUser(user).getTickets(), user);
    }

    @GetMapping
    public List<OrderResponseDto> getUserOrders(Authentication auth) {
        return orderService.getOrderHistory(userService.findByEmail(auth.getName()).get())
                .stream()
                .map(mapper::getResponseDtoFromOrder)
                .collect(Collectors.toList());
    }
}
