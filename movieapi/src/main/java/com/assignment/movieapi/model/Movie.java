package com.assignment.movieapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @Column(name = "release_year")
    String year;
    @Column(name = "category")
    String category;
    @Column(name = "nominee")
    String nominee;
    @Column(name = "additional_info")
    String additionalInfo;

    @Column(name = "won")
    String won;

}
