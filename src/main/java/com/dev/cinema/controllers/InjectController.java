package com.dev.cinema.controllers;

import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.time.LocalDateTime;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InjectController {
    private final CinemaHallService cinemaHallService;
    private final MovieService movieService;
    private final MovieSessionService movieSessionService;
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final OrderService orderService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public InjectController(CinemaHallService cinemaHallService,
                            MovieService movieService,
                            MovieSessionService movieSessionService,
                            AuthenticationService authenticationService,
                            UserService userService,
                            ShoppingCartService shoppingCartService,
                            OrderService orderService,
                            RoleService roleService,
                            PasswordEncoder passwordEncoder) {
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
        this.movieSessionService = movieSessionService;
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        insertRoles();
        insertDate();
    }

    private void insertRoles() {
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.add(userRole);
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        roleService.add(adminRole);
    }

    private void insertDate() {
        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setPassword(passwordEncoder.encode("password"));
        admin.setRoles(Set.of(roleService.getRoleByName("ADMIN")));
        userService.add(admin);

        authenticationService.register("user@gmail.com","userpass");
        authenticationService.register("fs@gmail.com", "fspass");

        Movie fastAndFurious = new Movie();
        fastAndFurious.setTitle("Fast and Furious");
        fastAndFurious.setDescription("Cars");
        movieService.add(fastAndFurious);

        Movie readyPlayerOne = new Movie();
        readyPlayerOne.setTitle("Ready Player One");
        fastAndFurious.setDescription("Games");
        movieService.add(readyPlayerOne);

        CinemaHall yellowCinemaHall = new CinemaHall();
        yellowCinemaHall.setCapacity(75);
        yellowCinemaHall.setDescription("yellow");
        cinemaHallService.add(yellowCinemaHall);

        CinemaHall blueCinemaHall = new CinemaHall();
        blueCinemaHall.setCapacity(120);
        blueCinemaHall.setDescription("blue");
        cinemaHallService.add(blueCinemaHall);

        MovieSession fastAndFuriousSession = new MovieSession();
        fastAndFuriousSession.setMovie(fastAndFurious);
        fastAndFuriousSession.setCinemaHall(yellowCinemaHall);
        fastAndFuriousSession.setShowTime(LocalDateTime.of(2020,6, 21, 19, 0));
        movieSessionService.add(fastAndFuriousSession);

        MovieSession readyPlayerOneSession = new MovieSession();
        readyPlayerOneSession.setMovie(readyPlayerOne);
        readyPlayerOneSession.setCinemaHall(blueCinemaHall);
        readyPlayerOneSession.setShowTime(LocalDateTime.of(2020,6, 22, 22, 0));
        movieSessionService.add(readyPlayerOneSession);

        var user1 = userService.findByEmail("user@gmail.com");
        var user2 = userService.findByEmail("fs@gmail.com");

        shoppingCartService.addSession(fastAndFuriousSession, user1);
        shoppingCartService.addSession(fastAndFuriousSession, user1);
        shoppingCartService.addSession(readyPlayerOneSession, user1);
        shoppingCartService.addSession(readyPlayerOneSession, user2);

        var byUser1 = shoppingCartService.getByUser(user1);
        var byUser2 = shoppingCartService.getByUser(user2);

        orderService.completeOrder(byUser1.getTickets(), user1);
        orderService.completeOrder(byUser2.getTickets(), user2);
    }
}
