package com.studbet.dao;

import com.studbet.model.Subject;

import java.util.List;

public interface SubjectDao {
    Subject save(Subject subject);
    Subject update(Subject subject);
    int delete(Subject subject);
    Subject findById(int id);
    List<Subject> getAll();
    Subject findByCode(String code);
}
