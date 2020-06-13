package com.dev.cinema.dao;

import com.dev.cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionDao {
    MovieSession add(MovieSession movieSession);

    MovieSession findById(Long id);

    List<MovieSession> findAvailableSession(Long movieId, LocalDate date);
}
