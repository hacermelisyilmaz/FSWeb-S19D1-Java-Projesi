package com.workintech.movie.service;

import com.workintech.movie.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();
    Movie findById(Long id);
    Movie save(Movie movie);
    Movie remove(Long id);
}
