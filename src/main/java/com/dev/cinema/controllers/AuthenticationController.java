package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.user.UserRequestDto;
import com.dev.cinema.security.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class AuthenticationController {
   private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public void register(@RequestBody UserRequestDto userRequestDto) {
        authenticationService.register(userRequestDto.getEmail(), userRequestDto.getPassword());
    }
}
