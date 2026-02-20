package com.studbet.dao.jooq.impl;

import com.studbet.dao.jooq.TransactionRepositoryJooq;
import com.studbet.model.Transaction;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryJooqImpl implements TransactionRepositoryJooq {
    private final DSLContext dsl;
    private final EntityManager em;

    @Override
    public List<Transaction> findByUserIdSorted(int userId) {
        return dsl.selectFrom(DSL.table("transactions"))
                .where(DSL.field("user_id").eq(userId))
                .orderBy(DSL.field("created_at").desc())
                .fetchInto(Transaction.class);
    }
}