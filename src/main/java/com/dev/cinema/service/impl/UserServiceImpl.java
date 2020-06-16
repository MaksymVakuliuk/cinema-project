package com.dev.cinema.service.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final HashUtil hashUtil;

    public UserServiceImpl(UserDao userDao, HashUtil hashUtil) {
        this.userDao = userDao;
        this.hashUtil = hashUtil;
    }

    @Override
    public User add(User user) {
        byte[] salt = hashUtil.getSalt();
        user.setSalt(salt);
        user.setPassword(hashUtil.hashPassword(user.getPassword(), salt));
        return userDao.add(user);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
