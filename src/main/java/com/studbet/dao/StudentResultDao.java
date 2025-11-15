package com.studbet.dao;

import com.studbet.model.StudentResult;

import java.util.List;

public interface StudentResultDao {
    StudentResult save(StudentResult result);
    StudentResult update(StudentResult result);
    int delete(StudentResult result);
    StudentResult findById(int id);
    List<StudentResult> getAll();
    List<StudentResult> findByUserId(int userId);
    List<StudentResult> findBySubjectId(int subjectId);
}
