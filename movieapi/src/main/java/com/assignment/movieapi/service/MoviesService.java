package com.assignment.movieapi.service;

import com.assignment.movieapi.dto.MoviesResponse;

public interface MoviesService {
    MoviesResponse findMovieWonBestpicture(String movieTitle);
}
