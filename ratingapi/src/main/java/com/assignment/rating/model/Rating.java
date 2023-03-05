package com.assignment.rating.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Entity(name = "rating")
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @NotBlank(message = "Movie title cannot be blank.")
    @Column(name = "movie_title")
    String movieTitle;
    @Column(name = "rating_score")
    Double ratingScore;

    @Column(name = "no_of_rating")
    long noOfRating;

    public Rating(String movieTitle, Double ratingScore, long noOfRating) {
        this.movieTitle = movieTitle;
        this.ratingScore = ratingScore;
        this.noOfRating = noOfRating;
    }


}
