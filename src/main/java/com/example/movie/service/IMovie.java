package com.example.movie.service;

import com.example.movie.entity.Movie;

import java.util.List;

public interface IMovie {

    List<Movie> getAllMovies();

    List<Movie> getAllMoviesByRatings(int ratingId);

    List<Movie> getAllMoviesByYear(int year);

    Movie saveMovie(Movie movie);

    Movie updateMovie(Movie movie, int id);

}
