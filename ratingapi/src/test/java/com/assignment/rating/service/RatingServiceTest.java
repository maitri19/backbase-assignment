package com.assignment.rating.service;

import com.assignment.rating.dto.OMDBResponse;
import com.assignment.rating.dto.RatingAndBoxOfficeResponse;
import com.assignment.rating.dto.RatingResponse;
import com.assignment.rating.dto.Top10RatingResponse;
import com.assignment.rating.feign.OmdbClient;
import com.assignment.rating.model.Rating;
import com.assignment.rating.repository.RatingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RatingServiceTest {
    @Mock
    RatingRepository ratingRepository;

    @InjectMocks
    RatingServiceImpl ratingService;

    @Mock
    ModelMapper modelMapper;

    @Mock
    OmdbClient omdbClient;

    @Value("${omdb.api.key}")
    String omdbApiKey;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testSaveOrUpdateRating_whenRatingProvided() {
        Rating newRating=new Rating("Beauty",5.0,2);
        Rating aRating=new Rating("Beauty",4.0,1);
        Mockito.when(ratingRepository.findRatingByMovieTitle(Mockito.any())).thenReturn(Optional.of(aRating));
        Mockito.when(ratingRepository.saveAndFlush(Mockito.any())).thenReturn(newRating);
        RatingResponse ratingResponse = ratingService.saveOrUpdateRating("Beauty",6.0);
        Assertions.assertEquals(5.0, ratingResponse.getOverallRating());
    }
    @Test
    void testGetTop10_whenNoRatingProvided() throws ParseException {
        Mockito.when(ratingRepository.findTop10ByOrderByRatingScoreDesc()).thenReturn(Optional.empty());
        Top10RatingResponse ratings = ratingService.findTop10RatedMovies();
        Assertions.assertEquals(0, ratings.getRatingAndBoxOffice().size());
    }

    @Test
    void testGetTop10_whenRatingsArePresent() throws ParseException {
        Rating beautyRating=new Rating("Beauty",5.0,2);
        Rating inceptionRating=new Rating("Inception",4.0,1);

        List<Rating> ratingList = new ArrayList<>();
        ratingList.add(beautyRating);
        ratingList.add(inceptionRating);

        RatingAndBoxOfficeResponse beautyRatingResponse = new RatingAndBoxOfficeResponse(beautyRating.getMovieTitle(), beautyRating.getRatingScore(), null);
        RatingAndBoxOfficeResponse inceptionRatingResponse = new RatingAndBoxOfficeResponse(inceptionRating.getMovieTitle(), inceptionRating.getRatingScore(), null);

        Mockito.when(ratingRepository.findTop10ByOrderByRatingScoreDesc()).thenReturn(Optional.of(ratingList));
        Mockito.when(modelMapper.map(beautyRating, RatingAndBoxOfficeResponse.class)).thenReturn(beautyRatingResponse);
        Mockito.when(modelMapper.map(inceptionRating, RatingAndBoxOfficeResponse.class)).thenReturn(inceptionRatingResponse);

        Mockito.when(omdbClient.getOmdbDetails(beautyRatingResponse.getMovieTitle(), omdbApiKey)).thenReturn(new OMDBResponse("$1,00,000"));
        Mockito.when(omdbClient.getOmdbDetails(inceptionRatingResponse.getMovieTitle(), omdbApiKey)).thenReturn(new OMDBResponse("$2,25,000"));

        Top10RatingResponse top10RatingResponse = ratingService.findTop10RatedMovies();
        Assertions.assertEquals(2, top10RatingResponse.getRatingAndBoxOffice().size());
    }
}
