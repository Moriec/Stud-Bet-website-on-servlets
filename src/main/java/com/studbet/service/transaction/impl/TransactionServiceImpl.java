package com.studbet.service.transaction.impl;

import com.studbet.dao.TransactionDao;
import com.studbet.model.Transaction;
import com.studbet.service.transaction.TransactionService;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionServiceImpl implements TransactionService {

    TransactionDao transactionDao;

    public TransactionServiceImpl(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Override
    public List<Transaction> findByUserId(int userId) {
        return transactionDao.findByUserId(userId).stream().sorted((a, b) -> -a.getCreatedAt().compareTo(b.getCreatedAt())).collect(Collectors.toList());
    }
}
