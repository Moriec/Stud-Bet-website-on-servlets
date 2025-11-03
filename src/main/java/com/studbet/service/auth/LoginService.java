package com.studbet.service.auth;

import com.studbet.model.User;
import com.studbet.security.session.UserSession;

public interface LoginService {
    UserSession findUserbyEmail(String email, String password);
    UserSession findUserbyUsername(String username, String password);
}
