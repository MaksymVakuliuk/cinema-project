package com.dev.cinema.dao;

import com.dev.cinema.model.User;

public interface UserDao {
    User add(User user);

    User findById(Long id);

    User findByEmail(String email);
}
