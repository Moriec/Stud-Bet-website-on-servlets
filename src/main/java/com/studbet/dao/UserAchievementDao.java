package com.studbet.dao;

import com.studbet.dao.jooq.UserAchievementRepositoryJooq;
import com.studbet.model.UserAchievement;
import com.studbet.model.UserAchievementId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserAchievementDao extends JpaRepository<UserAchievement, UserAchievementId>, UserAchievementRepositoryJooq {
    List<UserAchievement> findByUserId(int userId);
    List<UserAchievement> findByAchievementId(int achievementId);
}
