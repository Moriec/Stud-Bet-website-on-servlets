package com.studbet.service.main.impl;

import com.studbet.dao.BettingEventDao;
import com.studbet.dao.SubjectDao;
import com.studbet.enums.EventStatus;
import com.studbet.model.BettingEvent;
import com.studbet.model.Subject;
import com.studbet.service.main.MainPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainPageServiceImpl implements MainPageService {

    private final BettingEventDao bettingEventDao;
    private final SubjectDao subjectDao;

    @Override
    public List<BettingEvent> getAviableBettingEvents() {
        return bettingEventDao.findAvailableEvents();
    }

    @Override
    public Map<String, Subject> getSubjectsMap() {
        Map<String, Subject> subjectsMap = new HashMap<>();
        List<Subject> allSubjects = subjectDao.findAll();
        for (Subject subject : allSubjects) {
            subjectsMap.put(String.valueOf(subject.getId()), subject);
        }
        return subjectsMap;
    }
}
