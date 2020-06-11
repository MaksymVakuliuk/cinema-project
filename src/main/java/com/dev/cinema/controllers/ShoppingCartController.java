package com.dev.cinema.controllers;

import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.dto.shoppingcart.ShoppingCartMapper;
import com.dev.cinema.model.dto.shoppingcart.ShoppingCartResponseDto;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final ShoppingCartMapper shoppingCartMapper;
    private final MovieSessionService movieSessionService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, UserService userService, ShoppingCartMapper shoppingCartMapper, MovieSessionService movieSessionService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.shoppingCartMapper = shoppingCartMapper;
        this.movieSessionService = movieSessionService;
    }

    @PostMapping("/addmoviesession")
    public void add(@RequestParam(name = "movieSessionId", required = true) Long movieSessionId,
                    @RequestParam(name = "userId", required = true) Long userId) {
        shoppingCartService.addSession(movieSessionService.findById(movieSessionId),
                userService.findById(userId));
    }

    @GetMapping("/byuser")
    public ShoppingCartResponseDto getByUser(@RequestParam(name = "userId", required = true) Long userId) {
        return shoppingCartMapper.convertToResponseDto(shoppingCartService.getByUser(userService.findById(userId)));
    }
}
