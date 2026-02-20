package com.studbet.service.auth.impl;

import com.studbet.dao.UserDao;
import com.studbet.service.auth.CheckLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckLoginServiceImpl implements CheckLoginService {

    private final UserDao userDao;

    @Override
    public boolean checkUsername(String username) {
        if(username == null){
            throw new NullPointerException("Usename == null in CheckLoginServiceImpl");
        }
        return userDao.findByUsername(username) != null;
    }

    @Override
    public boolean checkEmail(String email) {
        if(email == null){
            throw new NullPointerException("Email == null in CheckLoginServiceImpl");
        }
        return userDao.findByEmail(email) != null;
    }
}
