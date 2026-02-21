package com.studbet.dao.jooq.impl;

import com.studbet.dao.jooq.BettingEventRepositoryJooq;
import com.studbet.model.BettingEvent;
import com.studbet.enums.EventStatus;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.studbet.jooq.codegen.Tables.BETTING_EVENTS;

@Repository
@RequiredArgsConstructor
public class BettingEventRepositoryJooqImpl implements BettingEventRepositoryJooq {
    private final DSLContext dsl;
    private final EntityManager em;

    @Override
    public List<BettingEvent> findAvailableEvents() {
        return dsl.selectFrom(BETTING_EVENTS)
                .where(BETTING_EVENTS.STATUS.eq(EventStatus.OPEN.name()))
                .fetchInto(BettingEvent.class);
    }
}