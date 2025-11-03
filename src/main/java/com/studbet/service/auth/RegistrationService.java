package com.studbet.service.auth;

import com.studbet.model.User;
import com.studbet.security.session.UserSession;

public interface RegistrationService {
    UserSession saveUser(User user);
}
