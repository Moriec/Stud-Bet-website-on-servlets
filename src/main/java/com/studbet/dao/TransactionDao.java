package com.studbet.dao;

import com.studbet.model.Transaction;

import java.util.List;

public interface TransactionDao {
    Transaction save(Transaction transaction);
    Transaction update(Transaction transaction);
    int delete(Transaction transaction);
    Transaction findById(int id);
    List<Transaction> getAll();
    List<Transaction> findByUserId(int userId);
    List<Transaction> findByRelatedBetId(int betId);
}
