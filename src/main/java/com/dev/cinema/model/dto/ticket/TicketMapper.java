package com.dev.cinema.model.dto.ticket;

import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.moviesession.MovieSessionMapper;
import com.dev.cinema.model.dto.user.UserMapper;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    private final MovieSessionService movieSessionService;
    private final UserService userService;
    private final MovieSessionMapper movieSessionMapper;
    private final UserMapper userMapper;

    public TicketMapper(MovieSessionService movieSessionService,
                        UserService userService,
                        MovieSessionMapper movieSessionMapper,
                        UserMapper userMapper) {
        this.movieSessionService = movieSessionService;
        this.userService = userService;
        this.movieSessionMapper = movieSessionMapper;
        this.userMapper = userMapper;
    }

    public Ticket convertFromRequestDto(TicketRequestDto ticketRequestDto) {
        var ticket = new Ticket();
        ticket.setMovieSession(movieSessionService
                .findById(ticketRequestDto.getMovieSessionId()));
        ticket.setUser(userService.findById(ticketRequestDto.getUserId()));
        return ticket;
    }

    public TicketResponseDto convertToResponseDto(Ticket ticket) {
        var ticketResponseDto = new TicketResponseDto();
        ticketResponseDto.setTickerId(ticket.getId());
        ticketResponseDto.setMovieSessionResponseDto(movieSessionMapper
                .convertToResponseDto(ticket.getMovieSession()));
        ticketResponseDto.setUserResponseDto(userMapper
                .convertToResponseDto(ticket.getUser()));
        return ticketResponseDto;
    }
}
