package com.studbet.service.transaction.impl;

import com.studbet.dao.TransactionDao;
import com.studbet.model.Transaction;
import com.studbet.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionDao transactionDao;

    @Override
    public List<Transaction> findByUserId(int userId) {
        return transactionDao.findByUserIdSorted(userId);
    }
}
