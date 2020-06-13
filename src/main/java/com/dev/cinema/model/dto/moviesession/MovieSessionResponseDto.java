package com.dev.cinema.model.dto.moviesession;

import com.dev.cinema.model.dto.cinemahall.CinemaHallResponseDto;
import com.dev.cinema.model.dto.movie.MovieResponseDto;

public class MovieSessionResponseDto {
    private Long movieSessionId;
    private MovieResponseDto movieResponseDto;
    private CinemaHallResponseDto cinemaHallResponseDto;
    private String showTime;

    public Long getMovieSessionId() {
        return movieSessionId;
    }

    public void setMovieSessionId(Long movieSessionId) {
        this.movieSessionId = movieSessionId;
    }

    public MovieResponseDto getMovieResponseDto() {
        return movieResponseDto;
    }

    public void setMovieResponseDto(MovieResponseDto movieResponseDto) {
        this.movieResponseDto = movieResponseDto;
    }

    public CinemaHallResponseDto getCinemaHallResponseDto() {
        return cinemaHallResponseDto;
    }

    public void setCinemaHallResponseDto(CinemaHallResponseDto cinemaHallResponseDto) {
        this.cinemaHallResponseDto = cinemaHallResponseDto;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
}
