package com.studbet.service.entity.impl;

import com.studbet.dao.AchevementsDao;
import com.studbet.model.Achievement;
import com.studbet.service.entity.AchievementService;

public class AchievementServiceImpl implements AchievementService {

    AchevementsDao achievementsDao;

    public AchievementServiceImpl(AchevementsDao achievementsDao) {
        this.achievementsDao = achievementsDao;
    }

    @Override
    public void createAchievement(Achievement achievement) {
        achievementsDao.save(achievement);
    }
}

