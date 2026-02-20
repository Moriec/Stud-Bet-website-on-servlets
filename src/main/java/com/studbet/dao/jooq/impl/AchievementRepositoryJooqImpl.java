package com.studbet.dao.jooq.impl;

import com.studbet.dao.jooq.AchievementRepositoryJooq;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AchievementRepositoryJooqImpl implements AchievementRepositoryJooq {
    private final DSLContext dsl;
    private final EntityManager em;
}