package com.dev.cinema;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MainApp {
    private static MovieService movieService =
            (MovieService) Injector.getInstance("com.dev.cinema").getInstance(MovieService.class);
    private static CinemaHallService hallService =
            (CinemaHallService) Injector.getInstance("com.dev.cinema")
                    .getInstance(CinemaHallService.class);
    private static MovieSessionService sessionService =
            (MovieSessionService) Injector.getInstance("com.dev.cinema")
                    .getInstance(MovieSessionService.class);
    private static UserService userService = (UserService) Injector.getInstance("com.dev.cinema")
            .getInstance(UserService.class);
    private static AuthenticationService authenticationService =
            (AuthenticationService) Injector.getInstance("com.dev.cinema")
            .getInstance(AuthenticationService.class);

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
        System.out.println(hallService.add(red));
        CinemaHall green = new CinemaHall();
        green.setCapacity(200);
        green.setDescription("GREEN");
        System.out.println(hallService.add(green));
        System.out.println(hallService.getAll());
        MovieSession sessionFirst = new MovieSession();
        sessionFirst.setCinemaHall(red);
        sessionFirst.setMovie(starWars);
        sessionFirst.setShowTime(LocalDateTime.of(2020, 10, 6, 15, 50));
        System.out.println(sessionService.add(sessionFirst));
        MovieSession sessionSecond = new MovieSession();
        sessionSecond.setCinemaHall(green);
        sessionSecond.setMovie(starWars);
        sessionSecond.setShowTime(LocalDateTime.of(2020, 10, 7, 23, 50));
        System.out.println(sessionService.add(sessionSecond));
        System.out.println(sessionService.findAvailableSessions(starWars.getId(),
                LocalDate.of(2020, 10, 6)));
        User bob = new User();
        bob.setEmail("Bob@gmail.com");
        bob.setPassword("123");
        try {
            System.out.println(authenticationService.register(bob.getEmail(), bob.getPassword()));
        } catch (AuthenticationException e) {
            System.out.println(e);
        }
        try {
            System.out.println(authenticationService.login(bob.getEmail(), bob.getPassword()));
        } catch (AuthenticationException e) {
            System.out.println(e);
        }
    }
}
