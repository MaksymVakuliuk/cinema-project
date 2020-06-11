package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.moviesession.MovieSessionMapper;
import com.dev.cinema.model.dto.moviesession.MovieSessionRequestDto;
import com.dev.cinema.model.dto.moviesession.MovieSessionResponseDto;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/moviesessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper movieSessionMapper;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieSessionMapper movieSessionMapper) {
        this.movieSessionService = movieSessionService;
        this.movieSessionMapper = movieSessionMapper;
    }

    @PostMapping
    public void add(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        movieSessionService.add(movieSessionMapper.convertFromRequestDto(movieSessionRequestDto));
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAvailable(
            @RequestParam(name = "movieId", required = true) Long movieId,
            @RequestParam(name = "date", required = true) String date) {
        return movieSessionService.findAvailableSession(movieId,
                LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .stream()
                .map(movieSessionMapper::convertToResponseDto)
                .collect(Collectors.toList());
    }
}
