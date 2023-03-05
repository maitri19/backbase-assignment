package com.assignment.movieapi.repository;

import com.assignment.movieapi.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Long> {

    public Optional<Movie> findMoviesByCategoryAndNomineeIgnoreCaseAndWonIgnoreCase(String category, String nominee, String won);

}
