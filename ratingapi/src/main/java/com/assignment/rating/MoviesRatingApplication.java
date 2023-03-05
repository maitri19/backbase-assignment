package com.assignment.rating;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableFeignClients
public class MoviesRatingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoviesRatingApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();

    }
}
