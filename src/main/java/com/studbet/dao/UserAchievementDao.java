package com.studbet.dao;

import com.studbet.model.UserAchievement;

import java.util.List;

public interface UserAchievementDao {
    UserAchievement save(UserAchievement userAchievement);
    UserAchievement update(UserAchievement userAchievement);
    int delete(UserAchievement userAchievement);
    UserAchievement findById(int userId, int achievementId);
    List<UserAchievement> getAll();
    List<UserAchievement> findByUserId(int userId);
    List<UserAchievement> findByAchievementId(int achievementId);
}
