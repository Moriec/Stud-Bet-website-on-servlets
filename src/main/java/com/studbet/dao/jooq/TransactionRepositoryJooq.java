package com.studbet.dao.jooq;

import com.studbet.model.Transaction;
import java.util.List;

public interface TransactionRepositoryJooq {
    List<Transaction> findByUserIdSorted(int userId);
}