package com.assignment.movieapi.controller;

import com.assignment.movieapi.dto.MoviesResponse;
import com.assignment.movieapi.exception.InvalidInputException;
import com.assignment.movieapi.service.MoviesService;
import com.assignment.movieapi.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movies")
public class MoviesRestController {

    @Autowired
    MoviesService moviesService;


    @GetMapping("/iswon")
    public ResponseEntity<MoviesResponse> getWonBestPicture(@RequestParam(value = "title") String movieTitle) {

        if(movieTitle.isBlank())
            throw new InvalidInputException(CommonConstants.TITLE_NOT_BLANK);
        return ResponseEntity.ok(moviesService.findMovieWonBestpicture(movieTitle));
    }
}
