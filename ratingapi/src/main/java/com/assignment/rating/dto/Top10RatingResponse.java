package com.assignment.rating.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Top10RatingResponse implements Serializable {
    List<RatingAndBoxOfficeResponse> ratingAndBoxOffice;

}
