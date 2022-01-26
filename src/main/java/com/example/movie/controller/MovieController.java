package com.example.movie.controller;

import com.example.movie.entity.Movie;
import com.example.movie.exception.MovieNotFoundException;
import com.example.movie.service.IMovie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/movie")
@Slf4j
public class MovieController {

    @Autowired
    IMovie iMovie;

    @PostMapping("/save")
    public ResponseEntity<Movie> saveMovie(@Valid @RequestBody Movie movie) {

        log.info("Saving Movie");
        return new ResponseEntity<>(iMovie.saveMovie(movie), HttpStatus.CREATED);

    }

    @PutMapping("update/{id}")
    public ResponseEntity<Movie> updateEmployee(@RequestBody Movie movie, @PathVariable("id") int movieId) {
        log.info("Updating Movie");
        return new ResponseEntity<>(iMovie.updateMovie(movie, movieId), HttpStatus.OK);

    }

    @GetMapping("/getAllMovies")
    public ResponseEntity<List<Movie>> getEmployeeList() {
        log.info("Get All Movie");
        return new ResponseEntity<>(iMovie.getAllMovies(), HttpStatus.OK);

    }


    @GetMapping("/getAllMoviesByYear/{yearId}")
    public ResponseEntity<List<Movie>> getAllMoviesByYear(@PathVariable("yearId") int yearId) {
        log.info("Get Movies By Year");
        List<Movie> allMoviesByYear = iMovie.getAllMoviesByYear(yearId);
        return new ResponseEntity<>(allMoviesByYear, HttpStatus.OK);


    }

    @GetMapping("/getAllMoviesByRatings/{ratingId}")
    public ResponseEntity<List<Movie>> getAllMoviesByRatings(@PathVariable("ratingId") int ratingId) {
        log.info("Get Movies By Ratings");
        List<Movie> allMoviesByRatings = iMovie.getAllMoviesByRatings(ratingId);
        return new ResponseEntity<>(allMoviesByRatings, HttpStatus.OK);

    }
}
