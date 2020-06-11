package com.dev.cinema.model.dto.ticket;

import com.dev.cinema.model.dto.moviesession.MovieSessionResponseDto;
import com.dev.cinema.model.dto.user.UserResponseDto;

public class TicketResponseDto {
    private Long tickerId;
    private MovieSessionResponseDto movieSessionResponseDto;
    private UserResponseDto userResponseDto;

    public Long getTickerId() {
        return tickerId;
    }

    public void setTickerId(Long tickerId) {
        this.tickerId = tickerId;
    }

    public MovieSessionResponseDto getMovieSessionResponseDto() {
        return movieSessionResponseDto;
    }

    public void setMovieSessionResponseDto(MovieSessionResponseDto movieSessionResponseDto) {
        this.movieSessionResponseDto = movieSessionResponseDto;
    }

    public UserResponseDto getUserResponseDto() {
        return userResponseDto;
    }

    public void setUserResponseDto(UserResponseDto userResponseDto) {
        this.userResponseDto = userResponseDto;
    }
}
