package com.example.movie.service;

import com.example.movie.entity.Movie;
import com.example.movie.exception.MovieNotFoundException;
import com.example.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements IMovie {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {

        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getAllMoviesByRatings(int ratingId) {
        List<Movie> byRatings = movieRepository.findByRatings(ratingId);
        if (byRatings.isEmpty()) {
            throw new MovieNotFoundException("Movie Not Found");
        }
        return byRatings;

    }

    @Override
    public List<Movie> getAllMoviesByYear(int yearId) {

        List<Movie> byYear = movieRepository.findByYear(yearId);
        if (byYear.isEmpty()) {
            throw new MovieNotFoundException("Movie Not Found");
        }
        return byYear;
    }

    @Override
    public Movie saveMovie(Movie movie) {
        if (movie.getMovieName().isEmpty()) {
            throw new MovieNotFoundException("Invalid Inputs");
        }
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie, int id) {
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found"));

        existingMovie.setMovieName(movie.getMovieName());
        existingMovie.setRatings(movie.getRatings());
        existingMovie.setYear(movie.getYear());
        return existingMovie;
    }


}
