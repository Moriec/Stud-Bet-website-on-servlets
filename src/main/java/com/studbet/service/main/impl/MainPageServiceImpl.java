package com.studbet.service.main.impl;

import com.studbet.dao.BettingEventDao;
import com.studbet.dao.SubjectDao;
import com.studbet.enums.EventStatus;
import com.studbet.model.BettingEvent;
import com.studbet.model.Subject;
import com.studbet.service.main.MainPageService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainPageServiceImpl implements MainPageService {

    BettingEventDao bettingEventDao;
    SubjectDao subjectDao;

    public MainPageServiceImpl(BettingEventDao bettingEventDao, SubjectDao subjectDao) {
        this.bettingEventDao = bettingEventDao;
        this.subjectDao = subjectDao;
    }

    @Override
    public List<BettingEvent> getAviableBettingEvents() {
        return bettingEventDao.getAll().stream()
                .filter(a -> a.getStatus() != null && a.getStatus().equals(EventStatus.OPEN.name()))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Subject> getSubjectsMap() {
        Map<String, Subject> subjectsMap = new HashMap<>();
        List<Subject> allSubjects = subjectDao.getAll();
        for (Subject subject : allSubjects) {
            subjectsMap.put(String.valueOf(subject.getId()), subject);
        }
        return subjectsMap;
    }
}
