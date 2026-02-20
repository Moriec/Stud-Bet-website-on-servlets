package com.studbet.dao;

import com.studbet.dao.jooq.BettingEventRepositoryJooq;
import com.studbet.model.BettingEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BettingEventDao extends JpaRepository<BettingEvent, Integer>, BettingEventRepositoryJooq {
    List<BettingEvent> findByTargetUserId(int targetUserId);
    List<BettingEvent> findBySubjectId(int subjectId);
    List<BettingEvent> findByStatus(String status);
}
