package com.workintech.movie.controller;

import com.workintech.movie.dto.ActorRequest;
import com.workintech.movie.dto.ActorResponse;
import com.workintech.movie.entity.Actor;
import com.workintech.movie.entity.Movie;
import com.workintech.movie.service.ActorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/actors")
public class ActorController {
    private final ActorService actorService;

    @GetMapping
    public List<ActorResponse> findAll() {
        List<Actor> allActors = actorService.findAll();
        List<ActorResponse> actors = new ArrayList<>();
        for (Actor actor: allActors) {
            actors.add(new ActorResponse(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getBirthDate()));
        }
        return actors;
    }

    @GetMapping("/{id}")
    public ActorResponse findById(@PathVariable Long id) {
        Actor actor = actorService.findById(id);
        return new ActorResponse(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getBirthDate());
    }

    @PostMapping
    public ActorResponse save(@RequestBody ActorRequest actorRequest) {
        Actor actor = actorRequest.getActor();
        List<Movie> movies = actorRequest.getMovies();

        for (Movie movie: movies) {
            actor.addMovie(movie);
        }

        actorService.save(actor);
        return new ActorResponse(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getBirthDate());
    }

    @PutMapping("/{id}")
    public ActorResponse update(@PathVariable Long id, @RequestBody Actor actor) {
        Actor existingActor = actorService.findById(id);
        actor.setMovies(existingActor.getMovies());
        actor.setId(id);
        actorService.save(actor);
        return new ActorResponse(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getBirthDate());
    }

    @DeleteMapping("/{id}")
    public ActorResponse remove(@PathVariable Long id) {
        Actor actor = actorService.findById(id);
        actorService.remove(id);
        return new ActorResponse(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getBirthDate());
    }
}
