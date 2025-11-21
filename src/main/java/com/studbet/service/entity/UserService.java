package com.studbet.service.entity;

import com.studbet.model.User;

public interface UserService {
    void createUser(User user);
    User getUserById(int id);
}

