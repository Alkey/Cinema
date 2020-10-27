package com.dev.cinema.controllers;

import com.dev.cinema.mapper.ShoppingCartMapper;
import com.dev.cinema.model.dto.ShoppingCartRequestDto;
import com.dev.cinema.model.dto.ShoppingCartResponseDto;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService cartService;
    private final UserService userService;
    private final MovieSessionService movieSessionService;
    private final ShoppingCartMapper mapper;

    @Autowired
    public ShoppingCartController(ShoppingCartService service,
                                  UserService userService,
                                  MovieSessionService movieSessionService,
                                  ShoppingCartMapper mapper) {
        this.cartService = service;
        this.userService = userService;
        this.movieSessionService = movieSessionService;
        this.mapper = mapper;
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(@RequestParam Long userId) {
        return mapper.getResponseDtoFromShoppingCart(
                cartService.getByUser(userService.getById(userId)));
    }

    @PostMapping("/movie-sessions")
    public void addMovieSession(@RequestBody ShoppingCartRequestDto dto) {
        cartService.addSession(movieSessionService.getById(dto.getMovieSessionId()),
                userService.getById(dto.getUserId()));
    }
}
