package com.studbet.service.entity.impl;

import com.studbet.dao.UserDao;
import com.studbet.model.User;
import com.studbet.security.password.PasswordEncrypt;
import com.studbet.service.entity.UserService;

public class UserServiceImpl implements UserService {

    UserDao userDao;
    PasswordEncrypt passwordEncrypt;

    public UserServiceImpl(UserDao userDao, PasswordEncrypt passwordEncrypt) {
        this.userDao = userDao;
        this.passwordEncrypt = passwordEncrypt;
    }

    @Override
    public void createUser(User user) {
        userDao.save(new User(
                user.getUsername(),
                user.getEmail(),
                passwordEncrypt.encryptPassword(user.getPassword()),
                user.getRole()
        ));
    }
}

