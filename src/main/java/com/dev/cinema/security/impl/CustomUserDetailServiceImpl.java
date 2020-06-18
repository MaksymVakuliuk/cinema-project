package com.dev.cinema.security.impl;

import com.dev.cinema.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailServiceImpl implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        var currentUser = userService.findByEmail(userName);

        UserBuilder userBuilder = null;
        if (currentUser != null) {
            userBuilder = User.withUsername(userName);
            userBuilder.password(currentUser.getPassword());
            userBuilder.roles(currentUser.getRoles()
                    .stream()
                    .map(role -> role.getRoleName().toString())
                    .toArray(String[]::new));
        } else {
            throw new UsernameNotFoundException("User not found!");
        }
        return userBuilder.build();
    }
}
