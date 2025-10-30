package com.studbet.model;

import java.time.LocalDateTime;

public class UserAchievement {
    int userId;
    int achievementId;
    LocalDateTime earned_at;

    public UserAchievement() {
    }

    public UserAchievement(int userId, int achievementId, LocalDateTime earned_at) {
        this.userId = userId;
        this.achievementId = achievementId;
        this.earned_at = earned_at;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(int achievementId) {
        this.achievementId = achievementId;
    }

    public LocalDateTime getEarned_at() {
        return earned_at;
    }

    public void setEarned_at(LocalDateTime earned_at) {
        this.earned_at = earned_at;
    }

    @Override
    public String toString() {
        return "UserAchievement{" +
                "userId=" + userId +
                ", achievementId=" + achievementId +
                ", earned_at=" + earned_at +
                '}';
    }
}
