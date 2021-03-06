package com.dev.cinema.service;

import com.dev.cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionService {
    MovieSession add(MovieSession movieSession);

    MovieSession findById(Long id);

    List<MovieSession> findAvailableSession(Long movieId, LocalDate date);
}
