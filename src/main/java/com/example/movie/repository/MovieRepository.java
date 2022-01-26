package com.example.movie.repository;

import com.example.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query("select m from Movie m where ratings like ?1")
    List<Movie> findByRatings(int ratingId);

    @Query("select m from Movie m where year like ?1")
    List<Movie> findByYear(int yearId);
}
