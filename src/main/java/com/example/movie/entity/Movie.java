package com.example.movie.entity;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "MOVIE_TLB")
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Movie name cannot be Null")
    @NotBlank(message = "Movie name cannot be Blank")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Invalid Input")
    @Size(min = 2, message = "Movie name must have atleast 2 charactes")
    String movieName;
    @NotNull(message = "Year  cannot be Null")
    @Min(value = 0, message = "The Year must be positive")
    @Column(length = 4)
    @Range(min = 1913, max = 2022)
    int year;
    @NotNull(message = "Ratings cannot be Null")
    @Min(value = 0, message = "The Ratings must be positive")
    int ratings;

}
