package com.assignment.rating.repository;

import com.assignment.rating.model.Rating;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(value = "classpath:application-test.properties")
@Sql(scripts = {"classpath:schema.sql"})
public class RatingRepositoryTest {

    @Autowired
    private RatingRepository ratingRepository;
    @Test
    void testFindRatingByMovieTitle(){
       Optional<Rating> rating= ratingRepository.findRatingByMovieTitle("Ray");
        if(rating.isPresent())
       assertEquals("Ray",rating.get().getMovieTitle());
    }
    @Test
    void testFindTop10RatedMovies(){
        Optional<List<Rating>> ratingList= ratingRepository.findTop10ByOrderByRatingScoreDesc();
        if(ratingList.isPresent())
            assertEquals(10,ratingList.get().size());
    }
    @Test
    void testFindRatingByMovieTitle_WhenRatingNotPresentForMovies(){
        Optional<Rating> rating= ratingRepository.findRatingByMovieTitle("RayMan");
        assertTrue(rating.isEmpty());
    }
}
