package com.studbet.service.entity.impl;

import com.studbet.dao.BettingEventDao;
import com.studbet.model.BettingEvent;
import com.studbet.service.entity.BettingEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BettingEventServiceImpl implements BettingEventService {

    private final BettingEventDao bettingEventDao;

    @Override
    public void createBettingEvent(BettingEvent bettingEvent) {
        bettingEventDao.save(bettingEvent);
    }
}

