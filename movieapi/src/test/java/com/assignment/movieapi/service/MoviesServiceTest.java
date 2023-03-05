package com.assignment.movieapi.service;

import com.assignment.movieapi.dto.MoviesResponse;
import com.assignment.movieapi.model.Movie;
import com.assignment.movieapi.repository.MoviesRepository;
import com.assignment.movieapi.util.CommonConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoviesServiceTest {
    @Mock
    MoviesRepository moviesRepository;
    @InjectMocks
    MoviesServieImpl moviesServie;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testFindMovieWonBestpicture_WhenMovieWon(){
        Optional<Movie> movie=Optional
                .of(new Movie(1,"2010 (83rd)", CommonConstants.BEST_PICTURE, "The King's Speech",
                        "Iain Canning, Emile Sherman and Gareth Unwin, Producers",CommonConstants.YES));
        Mockito.when(moviesRepository.findMoviesByCategoryAndNomineeIgnoreCaseAndWonIgnoreCase(
                Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenReturn(movie);
       MoviesResponse moviesResponse= moviesServie.findMovieWonBestpicture("The King's Speech");
       assertTrue(moviesResponse.getMovieWon().equals(CommonConstants.MOVIES_WON_BEST_PICTURE_RESPONSE));

    }
    @Test
    public void testFindMovieWonBestpicture_WhenMovieDidNotWin(){
        Optional<Movie> movie=Optional.empty();
        Mockito.when(moviesRepository.findMoviesByCategoryAndNomineeIgnoreCaseAndWonIgnoreCase(
                Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenReturn(movie);
        MoviesResponse moviesResponse= moviesServie.findMovieWonBestpicture("Black Swan");
        assertTrue(moviesResponse.getMovieWon().equals(CommonConstants.MOVIES_NOT_WON_BEST_PICTURE_RESPONSE));

    }
}
