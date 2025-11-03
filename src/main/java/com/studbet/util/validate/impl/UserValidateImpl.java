package com.studbet.util.validate.impl;

import com.studbet.util.validate.UserValidate;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.regex.Pattern;

public class UserValidateImpl implements UserValidate {

    private static final Pattern USERNAME_RE = Pattern.compile("^[a-zA-Z0-9_-]{3,30}$");

    private static final int PASS_MIN = 8;
    private static final int PASS_MAX = 50;

    @Override
    public boolean isUsernameValid(String username) {
        if (username == null) return false;
        if (!username.equals(username.trim())) return false;
        return USERNAME_RE.matcher(username).matches();
    }

    @Override
    public boolean isEmailValid(String email) {
        if (email == null) return false;
        if (!email.equals(email.trim())) return false;
        return EmailValidator.getInstance().isValid(email);

    }

    @Override
    public boolean isPasswordValid(String password) {
        if (password == null) return false;
        int len = password.length();
        return len >= PASS_MIN && len <= PASS_MAX;
    }
}