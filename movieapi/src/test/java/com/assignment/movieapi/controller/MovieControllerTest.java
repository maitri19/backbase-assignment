package com.assignment.movieapi.controller;

import com.assignment.movieapi.dto.MoviesResponse;
import com.assignment.movieapi.service.MoviesServieImpl;
import com.assignment.movieapi.util.CommonConstants;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MoviesRestController.class)
@AutoConfigureMockMvc(addFilters = false)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MoviesServieImpl moviesServie;
    @Mock
    MoviesResponse moviesResponse;

    @Test
    void testMovieWonOscar_whenWonYES() throws Exception {

        MoviesResponse moviesResponse = new MoviesResponse(CommonConstants.MOVIES_WON_BEST_PICTURE_RESPONSE);

        Mockito.when(moviesServie.findMovieWonBestpicture(Mockito.anyString())).thenReturn(moviesResponse);

        mockMvc.perform(get("/api/v1/movies/iswon").param("title","The King's Speech").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    @Test
    void testMovieWonOscar_whenWonNO() throws Exception {

        MoviesResponse moviesResponse = new MoviesResponse(CommonConstants.MOVIES_NOT_WON_BEST_PICTURE_RESPONSE);

        Mockito.when(moviesServie.findMovieWonBestpicture(Mockito.anyString())).thenReturn(moviesResponse);

        mockMvc.perform(get("/api/v1/movies/iswon").param("title","Ray").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    
    @Test
    void testMovieWonOscar_whenTitleIsBlank() throws Exception {

        MoviesResponse moviesResponse = new MoviesResponse(CommonConstants.MOVIES_NOT_WON_BEST_PICTURE_RESPONSE);

        Mockito.when(moviesServie.findMovieWonBestpicture(Mockito.anyString())).thenReturn(moviesResponse);

        mockMvc.perform(get("/api/v1/movies/iswon").param("title","").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }


}
