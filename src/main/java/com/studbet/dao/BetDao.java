package com.studbet.dao;

import com.studbet.dao.jooq.BetRepositoryJooq;
import com.studbet.model.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BetDao extends JpaRepository<Bet, Long>, BetRepositoryJooq {
    List<Bet> findByBettorId(int bettorId);
    List<Bet> findByEventId(int eventId);
    List<Bet> findByStatus(String status);
}
