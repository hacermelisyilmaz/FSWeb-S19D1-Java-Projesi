package com.workintech.movie.service;

import com.workintech.movie.entity.Movie;
import com.workintech.movie.exceptions.MovieException;
import com.workintech.movie.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if (movieOptional.isPresent()) return movieOptional.get();
        throw new MovieException("Movie with given ID is not found: " + id, HttpStatus.BAD_REQUEST);
    }

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie remove(Long id) {
        Movie movie = findById(id);
        movieRepository.delete(movie);
        return movie;
    }
}
