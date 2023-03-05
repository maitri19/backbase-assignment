package com.assignment.movieapi.repository;

import com.assignment.movieapi.model.Movie;
import com.assignment.movieapi.util.CommonConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(value = "classpath:application-test.properties")
public class MovieRepositoryTest {
    @Autowired
    private MoviesRepository moviesRepository;
    @Test
    void testFindMovieWonOscarOrNot_whenMovieWon(){
        Optional<Movie> movie= moviesRepository
                .findMoviesByCategoryAndNomineeIgnoreCaseAndWonIgnoreCase(CommonConstants.BEST_PICTURE,"Chicago",CommonConstants.YES);
        if(movie.isPresent())
            assertEquals(CommonConstants.YES,movie.get().getWon());
    }
    @Test
    void testFindMovieWonOscarOrNot_whenMovieDidNotWin(){
        Optional<Movie> movie= moviesRepository
                .findMoviesByCategoryAndNomineeIgnoreCaseAndWonIgnoreCase(CommonConstants.BEST_PICTURE,"Ray",CommonConstants.YES);
            assertTrue(movie.isEmpty());
    }
}
