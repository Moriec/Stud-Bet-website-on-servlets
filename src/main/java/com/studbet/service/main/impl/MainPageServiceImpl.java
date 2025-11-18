package com.studbet.service.main.impl;

import com.studbet.dao.BettingEventDao;
import com.studbet.dao.impl.BettingEventDaoImpl;
import com.studbet.enums.EventStatus;
import com.studbet.model.BettingEvent;
import com.studbet.service.main.MainPageService;

import java.util.List;
import java.util.stream.Collectors;

public class MainPageServiceImpl implements MainPageService {

    BettingEventDao bettingEventDao;

    public MainPageServiceImpl(BettingEventDao bettingEventDao) {
        this.bettingEventDao = bettingEventDao;
    }

    @Override
    public List<BettingEvent> getAviableBettingEvents() {
        return bettingEventDao.getAll().stream().filter(a -> a.getEventType().equals(EventStatus.OPEN.name())).collect(Collectors.toList());
    }
}
