package com.studbet.dao.jooq.impl;

import com.studbet.dao.jooq.BettingEventRepositoryJooq;
import com.studbet.model.BettingEvent;
import com.studbet.enums.EventStatus;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BettingEventRepositoryJooqImpl implements BettingEventRepositoryJooq {
    private final DSLContext dsl;
    private final EntityManager em;

    @Override
    public List<BettingEvent> findAvailableEvents() {
        return dsl.selectFrom(DSL.table("betting_events"))
                .where(DSL.field("status").eq(EventStatus.OPEN.name()))
                .fetchInto(BettingEvent.class);
    }
}