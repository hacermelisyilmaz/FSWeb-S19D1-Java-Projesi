package com.workintech.movie.service;

import com.workintech.movie.entity.Actor;
import com.workintech.movie.exceptions.MovieException;
import com.workintech.movie.repository.ActorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ActorServiceImpl implements ActorService {
    private final ActorRepository actorRepository;

    @Override
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    @Override
    public Actor findById(Long id) {
        Optional<Actor> actorOptional = actorRepository.findById(id);

        if (actorOptional.isPresent()) return actorOptional.get();
        throw new MovieException("Actor with the following ID is not found: " + id, HttpStatus.BAD_REQUEST);
    }

    @Override
    public Actor save(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public Actor remove(Long id) {
        Actor actor = findById(id);
        actorRepository.delete(actor);
        return actor;
    }
}
