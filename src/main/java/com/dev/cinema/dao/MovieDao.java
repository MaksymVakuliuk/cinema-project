package com.dev.cinema.dao;

import com.dev.cinema.model.Movie;
import java.util.List;

public interface MovieDao {
    Movie add(Movie movie);

    Movie findById(Long id);

    List<Movie> getAll();
}
