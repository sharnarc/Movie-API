package com.example.movie;

import com.example.movie.controller.MovieController;
import com.example.movie.entity.Movie;
import com.example.movie.service.IMovie;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = MovieController.class)

public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IMovie iMovie;


    @Test
    public void testGetAllMovies() throws Exception {

        Movie movie = getMovies();

        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        Mockito.when(iMovie.getAllMovies()).thenReturn(movieList);


        String URI = "/api/movie/getAllMovies";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URI).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedJson = this.mapToJson(movieList);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);


    }

    @Test
    public void testGetMovieByRating() throws Exception {
        Movie movie = getMovies();
        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        Mockito.when(iMovie.getAllMoviesByRatings(Mockito.anyInt())).thenReturn(movieList);

        String URI = "/api/movie/getAllMoviesByRatings/3";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URI).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.mapToJson(movieList);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
    }


    @Test
    public void testGetMovieByYear() throws Exception {
        Movie movie = getMovies();
        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        Mockito.when(iMovie.getAllMoviesByYear(Mockito.anyInt())).thenReturn(movieList);

        String URI = "/api/movie/getAllMoviesByYear/2003";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URI).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.mapToJson(movieList);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
    }

    @Test
    public void testSaveMovie() throws Exception {

        Movie movie = getMovies();
        String inputInJson = this.mapToJson(movie);
        String URI = "/api/movie/save";
        Mockito.when(iMovie.saveMovie(Mockito.any(Movie.class))).thenReturn(movie);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

        assertThat(outputInJson).isEqualTo(inputInJson);
    }


    private Movie getMovies() {

        return Movie.builder()
                .movieName("KKKG")
                .id(1)
                .ratings(3)
                .year(2003)
                .build();

    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }


}
