package com.studbet.dao;

import com.studbet.model.BettingEvent;

import java.util.List;

public interface BettingEventDao {
    BettingEvent save(BettingEvent event);
    BettingEvent update(BettingEvent event);
    int delete(BettingEvent event);
    BettingEvent findById(int id);
    List<BettingEvent> getAll();
    List<BettingEvent> findByTargetUserId(int userId);
    List<BettingEvent> findBySubjectId(int subjectId);
    List<BettingEvent> findByStatus(String status);
}
