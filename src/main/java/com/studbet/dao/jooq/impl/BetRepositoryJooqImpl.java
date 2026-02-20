package com.studbet.dao.jooq.impl;

import com.studbet.dao.jooq.BetRepositoryJooq;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BetRepositoryJooqImpl implements BetRepositoryJooq {
    private final DSLContext dsl;
    private final EntityManager em;
}