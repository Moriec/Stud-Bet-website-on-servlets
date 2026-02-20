package com.studbet.service.bet.impl;

import com.studbet.dao.BetDao;
import com.studbet.dao.BettingEventDao;
import com.studbet.dao.TransactionDao;
import com.studbet.dao.UserDao;
import com.studbet.enums.BetStatus;
import com.studbet.enums.EventStatus;
import com.studbet.enums.TransactionType;
import com.studbet.model.*;
import com.studbet.service.bet.BetCompleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BetCompleteServiceImpl implements BetCompleteService {

    private final UserDao userDao;
    private final BettingEventDao bettingEventDao;
    private final TransactionDao transactionDao;
    private final BetDao betDao;

    public void completeBet(StudentResult studentResult) {
        int targetUserId = studentResult.getUserId();
        int subjectId = studentResult.getSubjectId();
        int semestr =  studentResult.getSemestr();
        String academicYear = studentResult.getAcademicYear();

        List<BettingEvent> bettingEvents = bettingEventDao.findByTargetUserId(targetUserId).stream()
                .filter(bettingEvent -> bettingEvent.getSubjectId() == subjectId)
                .filter(bettingEvent -> bettingEvent.getStatus().equals(EventStatus.OPEN.name()))
                .filter(bettingEvent -> bettingEvent.getSemestr() == semestr)
                .filter(bettingEvent -> bettingEvent.getAcademicYear().equals(academicYear))
                .collect(Collectors.toList());
        //Обновим события
        for(BettingEvent bettingEvent : bettingEvents) {
            bettingEvent.setClosedIt(LocalDateTime.now());
            bettingEvent.setStatus(EventStatus.RESOLVED.name());
            bettingEventDao.save(bettingEvent);
        }

        //
        List<Integer> bettingEventsId = bettingEvents.stream().map(BettingEvent::getId).collect(Collectors.toList());
        List<Bet> bets = betDao.findByStatus(BetStatus.PENDING.name());

        for(Bet bet : bets) {
            if(bettingEventsId.contains(bet.getEventId())) {
                if(isWin(studentResult, bet)){
                    //Платим
                    User user = userDao.findById(bet.getBettorId()).orElse(null);
                    if (user != null) {
                        user.setBalance(user.getBalance() + bet.getPayoutAmount());
                        user.setRatingPoints(user.getRatingPoints() + bet.getPayoutAmount());
                        userDao.save(user);

                        Transaction transaction = new Transaction(user.getId(), TransactionType.BET_WIN.name(), bet.getPayoutAmount(), user.getBalance() - bet.getPayoutAmount(), user.getBalance(), "Успешная ставка");
                        transactionDao.save(transaction);
                    }

                    bet.setResolvedAt(LocalDateTime.now());
                    bet.setStatus(BetStatus.WON.name());
                    betDao.save(bet);
                }
                else{
                    bet.setResolvedAt(LocalDateTime.now());
                    bet.setStatus(BetStatus.LOST.name());
                    betDao.save(bet);
                }
            }
        }
    }

    private boolean isWin(StudentResult studentResult, Bet bet) {
        int actualScore = studentResult.getActualScore();
        int minI = bet.getPredictedScoreMin();
        int maxI = bet.getPredictedScoreMax();
        return actualScore >= minI && actualScore <= maxI;
    }
}
