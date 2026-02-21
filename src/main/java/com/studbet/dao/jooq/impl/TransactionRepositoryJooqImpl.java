package com.studbet.dao.jooq.impl;

import com.studbet.dao.jooq.TransactionRepositoryJooq;
import com.studbet.model.Transaction;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.studbet.jooq.codegen.Tables.TRANSACTIONS;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryJooqImpl implements TransactionRepositoryJooq {
    private final DSLContext dsl;
    private final EntityManager em;

    @Override
    public List<Transaction> findByUserIdSorted(int userId) {
        return dsl.selectFrom(TRANSACTIONS)
                .where(TRANSACTIONS.USER_ID.eq(userId))
                .orderBy(TRANSACTIONS.CREATED_AT.desc())
                .fetchInto(Transaction.class);
    }
}