package com.studbet.service.auth;

public interface CheckLoginService {
    boolean checkUsername(String username);
    boolean checkEmail(String password);
}
