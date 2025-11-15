package com.studbet.dao;

import com.studbet.model.Bet;

import java.util.List;

public interface BetDao {
    Bet save(Bet bet);
    Bet update(Bet bet);
    int delete(Bet bet);
    Bet findById(int id);
    List<Bet> getAll();
    List<Bet> findByBettorId(int bettorId);
    List<Bet> findByEventId(int eventId);
    List<Bet> findByStatus(String status);
}
