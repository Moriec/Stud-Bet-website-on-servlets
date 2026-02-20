package com.studbet.dao.jooq.impl;

import com.studbet.dao.jooq.SubjectRepositoryJooq;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SubjectRepositoryJooqImpl implements SubjectRepositoryJooq {
    private final DSLContext dsl;
    private final EntityManager em;
}