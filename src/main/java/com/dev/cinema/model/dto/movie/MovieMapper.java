package com.dev.cinema.model.dto.movie;

import com.dev.cinema.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    public Movie convertFromRequestDto(MovieRequestDto movieRequestDto) {
        var movie = new Movie();
        movie.setTitle(movieRequestDto.getTitle());
        movie.setDescription(movieRequestDto.getDescription());
        return movie;
    }

    public MovieResponseDto convertToResponseDto(Movie movie) {
        var movieResponseDto = new MovieResponseDto();
        movieResponseDto.setMovieId(movie.getId());
        movieResponseDto.setTitle(movie.getTitle());
        movieResponseDto.setDescription(movie.getDescription());
        return movieResponseDto;
    }
}
