package com.studbet.service.transaction;

import com.studbet.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> findByUserId(int userId);
}
