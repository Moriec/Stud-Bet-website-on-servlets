package com.studbet.dao.jooq;

import com.studbet.model.BettingEvent;
import java.util.List;

public interface BettingEventRepositoryJooq {
    List<BettingEvent> findAvailableEvents();
}