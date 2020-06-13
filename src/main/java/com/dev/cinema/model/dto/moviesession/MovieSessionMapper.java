package com.dev.cinema.model.dto.moviesession;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.cinemahall.CinemaHallMapper;
import com.dev.cinema.model.dto.movie.MovieMapper;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {
    private MovieService movieService;
    private CinemaHallService cinemaHallService;
    private MovieMapper movieMapper;
    private CinemaHallMapper cinemaHallMapper;

    public MovieSessionMapper(MovieService movieService,
                              CinemaHallService cinemaHallService,
                              MovieMapper movieMapper,
                              CinemaHallMapper cinemaHallMapper) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
        this.movieMapper = movieMapper;
        this.cinemaHallMapper = cinemaHallMapper;
    }

    public MovieSession convertFromRequestDto(MovieSessionRequestDto movieSessionRequestDto) {
        var movieSession = new MovieSession();
        movieSession.setMovie(movieService.findById(movieSessionRequestDto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService
                .findById(movieSessionRequestDto.getCinemaHallId()));
        movieSession.setShowTime(LocalDateTime.parse(movieSessionRequestDto.getShowTime()));
        return movieSession;
    }

    public MovieSessionResponseDto convertToResponseDto(MovieSession movieSession) {
        var movieSessionResponseDto = new MovieSessionResponseDto();
        movieSessionResponseDto.setMovieSessionId(movieSession.getId());
        movieSessionResponseDto.setMovieResponseDto(movieMapper
                .convertToResponseDto(movieSession.getMovie()));
        movieSessionResponseDto.setCinemaHallResponseDto(cinemaHallMapper
                .convertToResponseDto(movieSession.getCinemaHall()));
        movieSessionResponseDto.setShowTime(movieSession.getShowTime().toString());
        return movieSessionResponseDto;
    }
}
