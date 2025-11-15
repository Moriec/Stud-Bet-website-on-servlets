package com.studbet.dao;

import com.studbet.model.Achievement;

import java.util.List;

public interface AchevementsDao {
    Achievement save(Achievement achievement);
    Achievement update(Achievement achievement);
    int delete(Achievement achievement);
    Achievement findById(int id);
    List<Achievement> getAll();
}
