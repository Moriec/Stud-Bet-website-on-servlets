package com.studbet.dao;

import com.studbet.dao.jooq.UserRepositoryJooq;
import com.studbet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer>, UserRepositoryJooq {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
