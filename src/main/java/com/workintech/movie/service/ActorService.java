package com.workintech.movie.service;

import com.workintech.movie.entity.Actor;

import java.util.List;

public interface ActorService {
    List<Actor> findAll();
    Actor findById(Long id);
    Actor save(Actor actor);
    Actor remove(Long id);
}
