package com.studbet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "related_bet_id")
    private int relatedBetId;
    @Column(name = "transaction_type")
    private String transactionType;
    @Column(name = "amount")
    private int amount;
    @Column(name = "balance_before")
    private int balanceBefore;
    @Column(name = "balance_after")
    private int balanceAfter;
    @Column(name = "description")
    private String description;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Transaction(int userId, String transactionType, int amount, int balanceBefore, int balanceAfter, String description) {
        this.userId = userId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balanceBefore = balanceBefore;
        this.balanceAfter = balanceAfter;
        this.description = description;
    }

    public Transaction(int userId, int relatedBetId, String transactionType, int amount, int balanceBefore, int balanceAfter, String description) {
        this.userId = userId;
        this.relatedBetId = relatedBetId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balanceBefore = balanceBefore;
        this.balanceAfter = balanceAfter;
        this.description = description;
    }
}