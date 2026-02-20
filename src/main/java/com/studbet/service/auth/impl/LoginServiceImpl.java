package com.studbet.service.auth.impl;

import com.studbet.dao.UserDao;
import com.studbet.model.User;
import com.studbet.security.password.PasswordEncrypt;
import com.studbet.security.session.UserSession;
import com.studbet.service.auth.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserDao userDao;
    private final PasswordEncrypt passwordEncrypt;

    @Override
    public UserSession findUserbyEmail(String email, String password) {
        User user = userDao.findByEmail(email).orElse(null);

        if (user == null) {
            return null;
        }

        if(!passwordEncrypt.checkPassword(password, user.getPassword())){
            return null;
        }

        return new UserSession(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }

    @Override
    public UserSession findUserbyUsername(String username, String password) {
        User user = userDao.findByUsername(username).orElse(null);

        if (user == null) {
            return null;
        }

        if(!passwordEncrypt.checkPassword(password, user.getPassword())){
            return null;
        }

        return new UserSession(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }
}
