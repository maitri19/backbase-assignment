package com.assignment.rating.service;

import com.assignment.rating.dto.RatingResponse;
import com.assignment.rating.dto.Top10RatingResponse;

import java.text.ParseException;

public interface RatingService {

    public RatingResponse saveOrUpdateRating(String movieTitle, Double ratingByUser);

    public Top10RatingResponse findTop10RatedMovies() throws ParseException;
}
