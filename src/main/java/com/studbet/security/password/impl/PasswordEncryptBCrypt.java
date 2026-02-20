package com.studbet.security.password.impl;

import com.studbet.security.password.PasswordEncrypt;
import org.mindrot.jbcrypt.BCrypt;

import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptBCrypt implements PasswordEncrypt {
    @Override
    public String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
