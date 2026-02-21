package com.studbet.dao.jooq.impl;

import com.studbet.dao.jooq.UserRepositoryJooq;
import com.studbet.model.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.studbet.jooq.codegen.Tables.USERS;

@Repository
@RequiredArgsConstructor
public class UserRepositoryJooqImpl implements UserRepositoryJooq {
    private final DSLContext dsl;
    private final EntityManager em;

    @Override
    public List<User> findTop10ByRating() {
        return dsl.selectFrom(USERS)
                .orderBy(USERS.RATING_POINTS.desc())
                .limit(10)
                .fetchInto(User.class);
    }
}
