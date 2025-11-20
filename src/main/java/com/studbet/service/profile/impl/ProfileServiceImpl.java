package com.studbet.service.profile.impl;

import com.studbet.dao.UserDao;
import com.studbet.dto.ProfileUser;
import com.studbet.model.User;
import com.studbet.service.profile.ProfileService;

public class ProfileServiceImpl implements ProfileService {
    UserDao userDao;

    public ProfileServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public ProfileUser findById(int userId) {
        User user = userDao.findById(userId);
        if (user == null) {return null;}
        return new ProfileUser(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getBalance(),
                user.getRatingPoints(),
                user.getRegistrationDate(),
                user.getLastLoginDate()
        );
    }

    @Override
    public void updateName(ProfileUser profileUser) {
        User user = userDao.findById(profileUser.getId());
        user.setFirstname(profileUser.getFirstname());
        user.setLastname(profileUser.getLastname());
        userDao.update(user);
    }
}
