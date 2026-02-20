package com.studbet.model;

import java.io.Serializable;
import java.util.Objects;

public class UserAchievementId implements Serializable {
    private int userId;
    private int achievementId;

    public UserAchievementId() {
    }

    public UserAchievementId(int userId, int achievementId) {
        this.userId = userId;
        this.achievementId = achievementId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAchievementId that = (UserAchievementId) o;
        return userId == that.userId && achievementId == that.achievementId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, achievementId);
    }
}