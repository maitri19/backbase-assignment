package com.assignment.rating.repository;

import com.assignment.rating.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    Optional<Rating> findRatingByMovieTitle(String title);

    Optional<List<Rating>> findTop10ByOrderByRatingScoreDesc();

}
