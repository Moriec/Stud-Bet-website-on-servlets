package com.studbet.service.profile.impl;

import com.studbet.dao.UserDao;
import com.studbet.dto.ProfileUser;
import com.studbet.model.User;
import com.studbet.service.profile.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserDao userDao;

    @Override
    public ProfileUser findById(int userId) {
        User user = userDao.findById(userId).orElse(null);
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
        User user = userDao.findById(profileUser.getId()).orElse(null);
        if (user != null) {
            user.setFirstname(profileUser.getFirstname());
            user.setLastname(profileUser.getLastname());
            userDao.save(user);
        }
    }
}
