package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.MovieService;

public class MainApp {
    private static MovieService movieService =
            (MovieService) Injector.getInstance("com.dev.cinema").getInstance(MovieService.class);

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Star Wars");
        System.out.println(movieService.add(movie));
        movieService.getAll().forEach(System.out::println);
    }
}
