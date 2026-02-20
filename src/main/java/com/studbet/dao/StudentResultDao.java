package com.studbet.dao;

import com.studbet.dao.jooq.StudentResultRepositoryJooq;
import com.studbet.model.StudentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentResultDao extends JpaRepository<StudentResult, Integer>, StudentResultRepositoryJooq {
    List<StudentResult> findByUserId(int userId);
    List<StudentResult> findBySubjectId(int subjectId);
}
