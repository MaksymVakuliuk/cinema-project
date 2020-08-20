package com.dev.cinema.controllers;

import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InjectData {
    private final CinemaHallService cinemaHallService;
    private final MovieService movieService;
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public InjectData(CinemaHallService cinemaHallService,
                      MovieService movieService,
                      AuthenticationService authenticationService,
                      UserService userService,
                      RoleService roleService,
                      PasswordEncoder passwordEncoder) {
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        insertRoles();
        insertAdminUser();
        insertUsers();
        insertMovies();
        insertCinemaHall();
        insertCinemaHall();
    }

    private void insertRoles() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.add(userRole);
    }

    private void insertAdminUser() {
        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setPassword(passwordEncoder.encode("password"));
        admin.setRoles(Set.of(roleService.getRoleByName("ADMIN")));
        userService.add(admin);
    }

    private void insertUsers() {
        authenticationService.register("user@gmail.com", "userpass");
    }

    private void insertMovies() {
        Movie fastAndFurious = new Movie();
        fastAndFurious.setTitle("Fast and Furious");
        fastAndFurious.setDescription("Cars");
        movieService.add(fastAndFurious);

        Movie readyPlayerOne = new Movie();
        readyPlayerOne.setTitle("Ready Player One");
        readyPlayerOne.setDescription("Games");
        movieService.add(readyPlayerOne);
    }

    private void insertCinemaHall() {
        CinemaHall yellowCinemaHall = new CinemaHall();
        yellowCinemaHall.setCapacity(75);
        yellowCinemaHall.setDescription("yellow");
        cinemaHallService.add(yellowCinemaHall);

        CinemaHall blueCinemaHall = new CinemaHall();
        blueCinemaHall.setCapacity(120);
        blueCinemaHall.setDescription("blue");
        cinemaHallService.add(blueCinemaHall);
    }
}
