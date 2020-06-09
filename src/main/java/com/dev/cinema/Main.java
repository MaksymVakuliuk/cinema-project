package com.dev.cinema;

import com.dev.cinema.config.AppConfig;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static final AnnotationConfigApplicationContext context
            = new AnnotationConfigApplicationContext(AppConfig.class);
    private static final CinemaHallService cinemaHallService
            = context.getBean(CinemaHallService.class);
    private static final MovieSessionService movieSessionService
            = context.getBean(MovieSessionService.class);
    private static final AuthenticationService authenticationService
            = context.getBean(AuthenticationService.class);
    private static final UserService userService
            = context.getBean(UserService.class);
    private static final ShoppingCartService shoppingCartService
            = context.getBean(ShoppingCartService.class);
    private static final OrderService orderService
            = context.getBean(OrderService.class);

    public static void main(String[] args) {
        MovieService movieService = context.getBean(MovieService.class);
        Movie fastAndFurious = new Movie();
        fastAndFurious.setTitle("Fast and Furious");
        fastAndFurious.setDescription("Cars");
        movieService.add(fastAndFurious);

        Movie readyPlayerOne = new Movie();
        readyPlayerOne.setTitle("Ready Player One");
        fastAndFurious.setDescription("Games");
        movieService.add(readyPlayerOne);

        movieService.getAll().forEach(System.out::println);

        CinemaHall yellowCinemaHall = new CinemaHall();
        yellowCinemaHall.setCapacity(75);
        yellowCinemaHall.setDescription("yellow");
        cinemaHallService.add(yellowCinemaHall);

        CinemaHall blueCinemaHall = new CinemaHall();
        blueCinemaHall.setCapacity(120);
        blueCinemaHall.setDescription("blue");
        cinemaHallService.add(blueCinemaHall);

        cinemaHallService.getAll().forEach(System.out::println);

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

        LocalDate day1 = LocalDate.of(2020, 6,21);
        LocalDate day2 = LocalDate.of(2020, 6,22);
        movieSessionService.findAvailableSession(readyPlayerOne.getId(), day1)
                .forEach(System.out::println);
        movieSessionService.findAvailableSession(readyPlayerOne.getId(), day2)
                .forEach(System.out::println);
        movieSessionService.findAvailableSession(fastAndFurious.getId(), day1)
                .forEach(System.out::println);
        movieSessionService.findAvailableSession(fastAndFurious.getId(), day2)
                .forEach(System.out::println);

        authenticationService.register("user@gmail.com", "123");
        authenticationService.register("fs@gmail.com", "234");

        var user1 = userService.findByEmail("user@gmail.com");
        System.out.println(user1);
        var user2 = userService.findByEmail("fs@gmail.com");
        System.out.println(user2);

        shoppingCartService.addSession(fastAndFuriousSession, user1);
        shoppingCartService.addSession(fastAndFuriousSession, user1);
        shoppingCartService.addSession(readyPlayerOneSession, user1);
        shoppingCartService.addSession(readyPlayerOneSession, user2);

        var byUser1 = shoppingCartService.getByUser(user1);
        System.out.println("By user 1: " + byUser1.toString());
        var byUser2 = shoppingCartService.getByUser(user2);
        System.out.println("By user 2: " + byUser2.toString());

        orderService.completeOrder(byUser1.getTickets(), user1);
        orderService.completeOrder(byUser2.getTickets(), user2);
        var orders = orderService.getOrderHistory(user1);
        orders.stream().forEach(System.out::println);

        System.out.println("End");
    }
}
