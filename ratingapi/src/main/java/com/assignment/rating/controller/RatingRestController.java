package com.assignment.rating.controller;

import com.assignment.rating.dto.RatingResponse;
import com.assignment.rating.dto.Top10RatingResponse;
import com.assignment.rating.exception.InvalidInputException;
import com.assignment.rating.service.RatingService;
import com.assignment.rating.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.text.ParseException;


@RestController
@RequestMapping("/api/v1/rating")
public class RatingRestController {

    @Autowired
    RatingService ratingService;

    @PostMapping("/rateMovie")
    public ResponseEntity<RatingResponse> provideRatingForMovie(@RequestParam("title") String movieTitle,
                                                                @RequestParam("rating") @Valid @NotBlank Double rating) {

        if(movieTitle.isBlank())
            throw new InvalidInputException(CommonConstants.TITLE_NOT_BLANK);

        return ResponseEntity.ok(ratingService.saveOrUpdateRating(movieTitle,rating));
    }

    @GetMapping("/top10")
    public ResponseEntity<Top10RatingResponse> getTop10() throws ParseException {

        Top10RatingResponse top10RatingResponse=ratingService.findTop10RatedMovies();
        if(top10RatingResponse.getRatingAndBoxOffice().isEmpty()){
         return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(top10RatingResponse);
    }


}
