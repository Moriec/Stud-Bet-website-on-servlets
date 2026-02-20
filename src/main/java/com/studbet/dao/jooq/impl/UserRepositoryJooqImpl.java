package com.studbet.dao.jooq.impl;

import com.studbet.dao.jooq.UserRepositoryJooq;
import com.studbet.model.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryJooqImpl implements UserRepositoryJooq {
    private final DSLContext dsl;
    private final EntityManager em;

    @Override
    public List<User> findTop10ByRating() {
        return dsl.selectFrom(DSL.table("users"))
                .orderBy(DSL.field("rating_points").desc())
                .limit(10)
                .fetchInto(User.class);
    }
}
