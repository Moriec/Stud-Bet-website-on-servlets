package com.studbet.service.main;

import com.studbet.model.BettingEvent;
import com.studbet.model.Subject;

import java.util.List;
import java.util.Map;

public interface MainPageService {
    List<BettingEvent> getAviableBettingEvents();
    Map<String, Subject> getSubjectsMap();
}
