package com.assignment.movieapi.service;

import com.assignment.movieapi.dto.MoviesResponse;
import com.assignment.movieapi.model.Movie;
import com.assignment.movieapi.repository.MoviesRepository;
import com.assignment.movieapi.util.CommonConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MoviesServieImpl implements MoviesService{

    private static final String Best_Picture = "Best Picture";
    private static final String YES = "YES";

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    MoviesRepository moviesRepository;

    @Override
    public MoviesResponse findMovieWonBestpicture(String movieTitle) {

        Optional<Movie> resultSet = moviesRepository.
                findMoviesByCategoryAndNomineeIgnoreCaseAndWonIgnoreCase(MoviesServieImpl.Best_Picture, movieTitle, MoviesServieImpl.YES);

        if(resultSet.isPresent()){
            MoviesResponse moviesResponse=  new MoviesResponse(CommonConstants.MOVIES_WON_BEST_PICTURE_RESPONSE);

           return moviesResponse;
        }
        return new MoviesResponse(CommonConstants.MOVIES_NOT_WON_BEST_PICTURE_RESPONSE);
    }
}
