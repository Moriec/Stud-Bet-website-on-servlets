package com.studbet.dao.jooq;

import com.studbet.model.User;
import java.util.List;

public interface UserRepositoryJooq {
    List<User> findTop10ByRating();
}
