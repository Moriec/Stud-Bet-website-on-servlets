package com.studbet.dao;

import com.studbet.dao.jooq.TransactionRepositoryJooq;
import com.studbet.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionDao extends JpaRepository<Transaction, Integer>, TransactionRepositoryJooq {
    List<Transaction> findByUserId(int userId);
    List<Transaction> findByRelatedBetId(int relatedBetId);
}
