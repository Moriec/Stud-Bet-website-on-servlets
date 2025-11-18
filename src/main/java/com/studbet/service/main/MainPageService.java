package com.studbet.service.main;

import com.studbet.model.BettingEvent;

import java.util.List;

public interface MainPageService {
    List<BettingEvent> getAviableBettingEvents();
}
