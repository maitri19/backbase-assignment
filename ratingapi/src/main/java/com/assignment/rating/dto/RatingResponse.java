package com.assignment.rating.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponse implements Serializable {
    @JsonProperty("Movie Title")
    String movieTitle;

    @JsonProperty("Overall Rating")
    Double overallRating;

    @JsonProperty("Rating by You")
    Double ratingByUser;
}
