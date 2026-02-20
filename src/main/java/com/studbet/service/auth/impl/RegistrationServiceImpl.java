package com.studbet.service.auth.impl;

import com.studbet.dao.UserDao;
import com.studbet.model.User;
import com.studbet.security.password.PasswordEncrypt;
import com.studbet.security.session.UserSession;
import com.studbet.service.auth.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserDao  userDao;
    private final PasswordEncrypt passwordEncrypt;

    @Override
    public UserSession saveUser(User user) {
        // проверка, существует ли запись
        if(userDao.findByUsername(user.getUsername()) != null || userDao.findByEmail(user.getEmail()) != null){
            return  null;
        }

        // Сохранение
        User userSave = userDao.save(new User(
                user.getUsername(),
                user.getEmail(),
                passwordEncrypt.encryptPassword(user.getPassword()),
                user.getRole()
        ));

        if(userSave == null){
            return null;
        }

        return new UserSession(
                userSave.getId(),
                userSave.getUsername(),
                userSave.getEmail(),
                userSave.getRole()
        );
    }

}
