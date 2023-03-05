package com.assignment.rating.service;

import com.assignment.rating.dto.OMDBResponse;
import com.assignment.rating.dto.RatingAndBoxOfficeResponse;
import com.assignment.rating.dto.RatingResponse;
import com.assignment.rating.dto.Top10RatingResponse;
import com.assignment.rating.feign.OmdbClient;
import com.assignment.rating.model.Rating;
import com.assignment.rating.repository.RatingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    ModelMapper modelMapper;

    @Value("${omdb.api.key}")
    String omdbApiKey;

    @Autowired
    OmdbClient omdbClient;

    @Override
    public RatingResponse saveOrUpdateRating(String movieTitle, Double ratingByUser) {
        RatingResponse ratingResponse=new RatingResponse();

        Optional<Rating> rating= ratingRepository.findRatingByMovieTitle(movieTitle);

        if(rating.isPresent()){
            Rating newRating=rating.get();
            long existingNoOfRating=rating.get().getNoOfRating();
            double existingOverallRating=rating.get().getRatingScore();
            double newOverallRating= ((existingOverallRating * existingNoOfRating)+ratingByUser)/(existingNoOfRating+1);
            newRating.setNoOfRating(existingNoOfRating+1);
            newRating.setRatingScore(newOverallRating);
            newRating= ratingRepository.saveAndFlush(newRating);
            BigDecimal bd = new BigDecimal(newRating.getRatingScore()).setScale(2, RoundingMode.HALF_UP);
            double newScore = bd.doubleValue();
            return new RatingResponse(movieTitle,newScore,ratingByUser);
        }
        Rating newRating=new Rating();
        newRating.setMovieTitle(movieTitle);
        newRating.setNoOfRating(1);
        newRating.setRatingScore(ratingByUser);
        newRating= ratingRepository.saveAndFlush(newRating);
        return new RatingResponse(movieTitle,newRating.getRatingScore(),ratingByUser);
    }

    @Override
    public Top10RatingResponse findTop10RatedMovies() throws ParseException {
        Optional<List<Rating>> ratingList= ratingRepository.findTop10ByOrderByRatingScoreDesc();
        if(ratingList.isPresent()){
            List<RatingAndBoxOfficeResponse> ratingReponseList = ratingList.get().parallelStream()
                    .map(r -> modelMapper.map(r,RatingAndBoxOfficeResponse.class)).collect(Collectors.toList());

            for (RatingAndBoxOfficeResponse ratingAndBoxOfficeResponse: ratingReponseList) {
                OMDBResponse omdbResponse = omdbClient.getOmdbDetails(ratingAndBoxOfficeResponse.getMovieTitle(), omdbApiKey);

                ratingAndBoxOfficeResponse.setBoxOffice(NumberFormat.getNumberInstance(Locale.US)
                        .parse(omdbResponse.getBoxOffice().substring(1)).longValue());
            }
            ratingReponseList.sort((a,b)-> a.getBoxOffice().compareTo(b.getBoxOffice()));
        return new Top10RatingResponse(ratingReponseList);
        }
        return new Top10RatingResponse(new ArrayList<RatingAndBoxOfficeResponse>());
    }
}
