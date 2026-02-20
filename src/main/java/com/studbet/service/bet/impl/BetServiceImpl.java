package com.studbet.service.bet.impl;

import com.studbet.dao.*;
import com.studbet.enums.BetStatus;
import com.studbet.enums.TransactionType;
import com.studbet.model.*;
import com.studbet.service.bet.BetService;
import com.studbet.util.calculator.NormalCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BetServiceImpl implements BetService {

    private final BetDao betDao;
    private final BettingEventDao bettingEventDao;
    private final UserDao userDao;
    private final SubjectDao subjectDao;
    private final TransactionDao transactionDao;
    private final NormalCalculator normalCalculator;

    @Override
    public double calculateOdds(int betAmount, int predictedScoreMin, int predictedScoreMax, int maxScore) {
        if (betAmount <= 0 || predictedScoreMin < 0 || predictedScoreMax > maxScore || predictedScoreMin > predictedScoreMax) {
            return 1.0;
        }

        double chance = normalCalculator.calculateProbability(predictedScoreMin, predictedScoreMax);
        double odds = calculateBettingOdds(chance);
        double koef = Math.round(odds * 100.0) / 100.0;
        if(koef < 0) {
            koef = 98;
        }
        if(koef > 100){
            koef = 98;
        }
        if(koef < 1){
            return 1.0;
        }
        return (Math.sqrt(koef) - 1) + Math.sqrt(koef);
    }

    private double calculateBettingOdds(double probability) {
        double margin = 0.22;
        double adjusted = probability * (1 + margin);
        double odds = 1.0 / adjusted;
        return odds;
    }

    @Override
    public Bet placeBet(Bet bet, User user) {
        BettingEvent event = bettingEventDao.findById(bet.getEventId()).orElse(null);
        if (event == null || !"OPEN".equals(event.getStatus())) {
            return null;
        }

        if (user.getBalance() < bet.getBetAmount()) {
            return null;
        }

        Subject subject = subjectDao.findById(event.getSubjectId()).orElse(null);
        int maxScore = subject != null ? subject.getMaxScore() : 100;

        double odds = calculateOdds(bet.getBetAmount(), bet.getPredictedScoreMin(), bet.getPredictedScoreMax(), maxScore);
        int payoutAmount = (int) Math.round(bet.getBetAmount() * odds);

        bet.setStatus(BetStatus.PENDING.name());
        bet.setPayoutAmount(payoutAmount);
        Bet savedBet = betDao.save(bet);
        
        if (savedBet != null) {
            Transaction transaction = new Transaction(user.getId(), savedBet.getId().intValue(), TransactionType.BET_PLACED.name(), -savedBet.getBetAmount(), user.getBalance(), user.getBalance() - savedBet.getBetAmount(), "Ставка");
            user.setBalance(user.getBalance() - bet.getBetAmount());
            user = userDao.save(user);
            if(user != null) {
                transactionDao.save(transaction);
            }
        }

        return savedBet;
    }

    @Override
    public BettingEvent getBettingEventById(int eventId) {
        return bettingEventDao.findById(eventId).orElse(null);
    }


    public List<Map<String, Object>> getUserBetsWithEvents(int userId) {
        List<Bet> bets = betDao.findByBettorId(userId);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Bet bet : bets) {
            BettingEvent event = bettingEventDao.findById(bet.getEventId()).orElse(null);

            Map<String, Object> betData = new HashMap<>();
            betData.put("bet", bet);
            betData.put("event", event);

            result.add(betData);
        }

        return result;
    }

    public Map<String, Integer> getUserBetsStats(int userId) {
        List<Bet> bets = betDao.findByBettorId(userId);

        int totalBets = bets.size();
        int wonBets = 0;
        int lostBets = 0;
        int activeBets = 0;
        int totalBetAmount = 0;
        int totalPayout = 0;

        for (Bet bet : bets) {
            totalBetAmount += bet.getBetAmount();
            totalPayout += bet.getPayoutAmount();

            if ("WON".equals(bet.getStatus())) {
                wonBets++;
            } else if ("LOST".equals(bet.getStatus())) {
                lostBets++;
            } else if ("PENDING".equals(bet.getStatus()) || "ACTIVE".equals(bet.getStatus())) {
                activeBets++;
            }
        }

        Map<String, Integer> stats = new HashMap<>();
        stats.put("totalBets", totalBets);
        stats.put("wonBets", wonBets);
        stats.put("lostBets", lostBets);
        stats.put("activeBets", activeBets);
        stats.put("totalBetAmount", totalBetAmount);
        stats.put("totalPayout", totalPayout);
        stats.put("profit", totalPayout - totalBetAmount);

        return stats;
    }
}

