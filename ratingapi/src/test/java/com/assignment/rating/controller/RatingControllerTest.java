package com.assignment.rating.controller;

import com.assignment.rating.dto.RatingAndBoxOfficeResponse;
import com.assignment.rating.dto.RatingResponse;
import com.assignment.rating.dto.Top10RatingResponse;
import com.assignment.rating.service.RatingServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RatingRestController.class)
@AutoConfigureMockMvc(addFilters = false)
public class RatingControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private RatingServiceImpl ratingService;
        @Mock
        RatingResponse ratingResponse;

        @Test
        void testSaveOrUpdate() throws Exception {

            RatingResponse ratingResponse = new RatingResponse("True Grit",4.0,4.0);

            Mockito.when(ratingService.saveOrUpdateRating(Mockito.anyString(),Mockito.any())).thenReturn(ratingResponse);

            mockMvc.perform(post("/api/v1/rating/rateMovie?title=True Grit&rating=4", "").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk()).andExpect(status().is2xxSuccessful());

        }

        @Test
        void testGetTop10_whenRatingPresent() throws Exception {

            RatingAndBoxOfficeResponse ratingAndBoxOfficeResponse=new RatingAndBoxOfficeResponse("Biutiful",5.5,23244l);
            List<RatingAndBoxOfficeResponse> ratingResponses = new ArrayList<>();
            ratingResponses.add(ratingAndBoxOfficeResponse);
            Top10RatingResponse topRatingResponse = new Top10RatingResponse(ratingResponses);

            Mockito.when(ratingService.findTop10RatedMovies()).thenReturn(topRatingResponse);

            mockMvc.perform(
                            get("/api/v1/rating/top10").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk()).andExpect(status().is2xxSuccessful());
        }
    @Test
    void testGetTop10_whenRatingNotPresent() throws Exception {

        List<RatingAndBoxOfficeResponse> ratingResponses = new ArrayList<>();
        Top10RatingResponse topRatingResponse = new Top10RatingResponse(ratingResponses);

        Mockito.when(ratingService.findTop10RatedMovies()).thenReturn(topRatingResponse);

        mockMvc.perform(
                        get("/api/v1/rating/top10").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent()).andExpect(status().is2xxSuccessful());
    }

}
