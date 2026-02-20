package com.studbet.service.entity.impl;

import com.studbet.dao.UserDao;
import com.studbet.model.User;
import com.studbet.security.password.PasswordEncrypt;
import com.studbet.service.entity.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncrypt passwordEncrypt;

    @Override
    public void createUser(User user) {
        userDao.save(new User(
                user.getUsername(),
                user.getEmail(),
                passwordEncrypt.encryptPassword(user.getPassword()),
                user.getRole()
        ));
    }

    @Override
    public User getUserById(int id) {
        return userDao.findById(id).orElse(null);
    }


}

