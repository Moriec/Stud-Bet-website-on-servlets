package com.studbet.service.bet;

import com.studbet.model.Bet;
import com.studbet.model.BettingEvent;
import com.studbet.model.User;

import java.util.List;
import java.util.Map;

public interface BetService {
    double calculateOdds(int betAmount, int predictedScoreMin, int predictedScoreMax, int maxScore);

    Bet placeBet(Bet bet, User user);

    BettingEvent getBettingEventById(int eventId);

    List<Map<String, Object>> getUserBetsWithEvents(int userId);

    Map<String, Integer> getUserBetsStats(int userId);
}

