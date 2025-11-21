package com.studbet.service.bet.impl;

import com.studbet.dao.*;
import com.studbet.enums.BetStatus;
import com.studbet.enums.TransactionType;
import com.studbet.model.*;
import com.studbet.service.bet.BetService;

public class BetServiceImpl implements BetService {

    private final BetDao betDao;
    private final BettingEventDao bettingEventDao;
    private final UserDao userDao;
    private final SubjectDao subjectDao;
    private final TransactionDao transactionDao;

    public BetServiceImpl(BetDao betDao, BettingEventDao bettingEventDao, UserDao userDao, SubjectDao subjectDao, TransactionDao transactionDao) {
        this.betDao = betDao;
        this.bettingEventDao = bettingEventDao;
        this.userDao = userDao;
        this.subjectDao = subjectDao;
        this.transactionDao = transactionDao;
    }

    @Override
    public double calculateOdds(int betAmount, int predictedScoreMin, int predictedScoreMax, int maxScore) {
        if (betAmount <= 0 || predictedScoreMin < 0 || predictedScoreMax > maxScore || predictedScoreMin > predictedScoreMax) {
            return 1.0;
        }

        double rangeWidth = (double)(predictedScoreMax - predictedScoreMin + 1) / maxScore;
        double baseOdds = 1.0 + (1.0 - rangeWidth) * 3.0;
        double betBonus = Math.min(0.5, betAmount / 1000.0 * 0.1);
        double finalOdds = baseOdds + betBonus;
        return Math.round(finalOdds * 100.0) / 100.0;
    }

    @Override
    public Bet placeBet(Bet bet, User user) {
        BettingEvent event = bettingEventDao.findById(bet.getEventId());
        if (event == null || !"OPEN".equals(event.getStatus())) {
            return null;
        }

        if (user.getBalance() < bet.getBetAmount()) {
            return null;
        }

        Subject subject = subjectDao.findById(event.getSubjectId());
        int maxScore = subject != null ? subject.getMaxScore() : 100;

        double odds = calculateOdds(bet.getBetAmount(), bet.getPredictedScoreMin(), bet.getPredictedScoreMax(), maxScore);
        int payoutAmount = (int) Math.round(bet.getBetAmount() * odds);

        bet.setStatus(BetStatus.PENDING.name());
        bet.setPayoutAmount(payoutAmount);
        Bet savedBet = betDao.save(bet);
        
        if (savedBet != null) {
            Transaction transaction = new Transaction(user.getId(), savedBet.getId(), TransactionType.BET_PLACED.name(), -savedBet.getBetAmount(), user.getBalance(), user.getBalance() - savedBet.getBetAmount(), "Ставка");
            user.setBalance(user.getBalance() - bet.getBetAmount());
            user = userDao.update(user);
            if(user != null) {
                transactionDao.save(transaction);
            }
        }

        return savedBet;
    }

    @Override
    public BettingEvent getBettingEventById(int eventId) {
        return bettingEventDao.findById(eventId);
    }
}

