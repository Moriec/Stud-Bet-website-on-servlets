package com.studbet.service.entity.impl;

import com.studbet.dao.AchevementsDao;
import com.studbet.model.Achievement;
import com.studbet.service.entity.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchevementsDao achievementsDao;

    @Override
    public void createAchievement(Achievement achievement) {
        achievementsDao.save(achievement);
    }
}

