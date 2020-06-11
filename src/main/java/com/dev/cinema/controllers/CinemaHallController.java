package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.cinemahall.CinemaHallMapper;
import com.dev.cinema.model.dto.cinemahall.CinemaHallRequestDto;
import com.dev.cinema.model.dto.cinemahall.CinemaHallResponseDto;
import com.dev.cinema.service.CinemaHallService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cinemahalls")
public class CinemaHallController {
    private final CinemaHallService cinemaHallService;
    private final CinemaHallMapper cinemaHallMapper;

    public CinemaHallController(CinemaHallService cinemaHallService, CinemaHallMapper cinemaHallMapper) {
        this.cinemaHallService = cinemaHallService;
        this.cinemaHallMapper = cinemaHallMapper;
    }

    @PostMapping
    public void add(@RequestBody CinemaHallRequestDto cinemaHallRequestDto) {
        cinemaHallService.add(cinemaHallMapper.convertFromRequestDto(cinemaHallRequestDto));
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAll() {
        return cinemaHallService.getAll().stream()
                .map(cinemaHallMapper::convertToResponseDto)
                .collect(Collectors.toList());
    }


}
