package com.studbet.dao;

import com.studbet.dao.jooq.SubjectRepositoryJooq;
import com.studbet.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SubjectDao extends JpaRepository<Subject, Integer>, SubjectRepositoryJooq {
    Optional<Subject> findByCode(String code);
}
