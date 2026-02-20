package com.studbet.dao;

import com.studbet.dao.jooq.AchievementRepositoryJooq;
import com.studbet.model.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchevementsDao extends JpaRepository<Achievement, Long>, AchievementRepositoryJooq {
}
