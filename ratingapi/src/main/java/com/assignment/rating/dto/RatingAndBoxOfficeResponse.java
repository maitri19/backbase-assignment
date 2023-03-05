package com.assignment.rating.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingAndBoxOfficeResponse implements Serializable {
    @JsonProperty("Movie Title")
    String movieTitle;

    @JsonProperty("Rating")
    double ratingScore;

    @JsonProperty("Box Office Value")
    Long boxOffice;

}
