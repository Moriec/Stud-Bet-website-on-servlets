package com.studbet.service.entity.impl;

import com.studbet.dao.BettingEventDao;
import com.studbet.model.BettingEvent;
import com.studbet.service.entity.BettingEventService;

public class BettingEventServiceImpl implements BettingEventService {

    BettingEventDao bettingEventDao;

    public BettingEventServiceImpl(BettingEventDao bettingEventDao) {
        this.bettingEventDao = bettingEventDao;
    }

    @Override
    public void createBettingEvent(BettingEvent bettingEvent) {
        bettingEventDao.save(bettingEvent);
    }
}

