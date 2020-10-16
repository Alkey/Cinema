package com.dev.cinema;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class MainApp {
    private static final Injector INJECTOR = Injector.getInstance("com.dev.cinema");
    private static Logger logger = Logger.getLogger(MainApp.class);
    private static MovieService movieService =
            (MovieService) INJECTOR.getInstance(MovieService.class);
    private static CinemaHallService hallService =
            (CinemaHallService) INJECTOR.getInstance(CinemaHallService.class);
    private static MovieSessionService sessionService =
            (MovieSessionService) INJECTOR.getInstance(MovieSessionService.class);
    private static UserService userService = (UserService)
            INJECTOR.getInstance(UserService.class);
    private static AuthenticationService authenticationService =
            (AuthenticationService) INJECTOR.getInstance(AuthenticationService.class);
    private static ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private static OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);

    public static void main(String[] args) {
        Movie starWars = new Movie();
        starWars.setTitle("Star Wars");
        starWars.setDescription("IMAX");
        movieService.add(starWars);
        Movie starTrack = new Movie();
        starTrack.setTitle("Star Track");
        starTrack.setDescription("3D");
        movieService.add(starTrack);
        CinemaHall red = new CinemaHall();
        red.setCapacity(100);
        red.setDescription("RED");
        hallService.add(red);
        CinemaHall green = new CinemaHall();
        green.setCapacity(200);
        green.setDescription("GREEN");
        hallService.add(green);
        hallService.getAll().forEach(logger::info);
        MovieSession sessionFirst = new MovieSession();
        sessionFirst.setCinemaHall(red);
        sessionFirst.setMovie(starWars);
        sessionFirst.setShowTime(LocalDateTime.of(2020, 10, 6, 15, 50));
        sessionService.add(sessionFirst);
        MovieSession sessionSecond = new MovieSession();
        sessionSecond.setCinemaHall(green);
        sessionSecond.setMovie(starWars);
        sessionSecond.setShowTime(LocalDateTime.of(2020, 10, 7, 23, 50));
        sessionService.add(sessionSecond);
        sessionService.findAvailableSessions(starWars.getId(),
                LocalDate.of(2020, 10, 6)).forEach(logger::info);
        User bob = new User();
        bob.setEmail("Bob@gmail.com");
        bob.setPassword("123");
        try {
            logger.info(authenticationService.register(bob.getEmail(), bob.getPassword()));
        } catch (AuthenticationException e) {
            logger.error(e);
        }
        try {
            logger.info(authenticationService.login(bob.getEmail(), bob.getPassword()));
        } catch (AuthenticationException e) {
            logger.error(e);
        }
        User user = userService.findByEmail(bob.getEmail()).get();
        shoppingCartService.addSession(sessionFirst, user);
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        List<Ticket> tickets = new ArrayList<>(shoppingCart.getTickets());
        Order order = orderService.completeOrder(tickets, user);
        orderService.getOrderHistory(user).forEach(logger::info);
    }
}
