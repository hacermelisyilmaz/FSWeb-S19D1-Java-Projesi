package com.workintech.movie.controller;

import com.workintech.movie.dto.MovieResponse;
import com.workintech.movie.entity.Movie;
import com.workintech.movie.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public List<MovieResponse> findAll() {
        List<Movie> allMovies = movieService.findAll();
        List<MovieResponse> movies = new ArrayList<>();
        for (Movie movie : allMovies) {
            movies.add(new MovieResponse(movie.getName(), movie.getDirectorName()));
        }
        return movies;
    }

    @GetMapping("/{id}")
    public MovieResponse findById(@PathVariable Long id) {
        Movie movie = movieService.findById(id);
        return new MovieResponse(movie.getName(), movie.getDirectorName());
    }

    @PostMapping
    public MovieResponse save(@RequestBody Movie movie) {
        movieService.save(movie);
        return new MovieResponse(movie.getName(), movie.getDirectorName());
    }

    @PutMapping("/{id}")
    public MovieResponse update(@PathVariable Long id, @RequestBody Movie movie) {
        Movie existingMovie = movieService.findById(id);
        movie.setActors(existingMovie.getActors());
        movie.setId(id);
        movieService.save(movie);
        return new MovieResponse(movie.getName(), movie.getDirectorName());
    }

    @DeleteMapping("/{id}")
    public MovieResponse remove(@PathVariable Long id) {
        Movie movie = movieService.findById(id);
        movieService.remove(id);
        return new MovieResponse(movie.getName(), movie.getDirectorName());
    }
}
