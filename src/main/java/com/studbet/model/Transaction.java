package com.studbet.model;

import java.time.LocalDateTime;

public class Transaction {
    private int id;
    private int userId;
    private int relatedBetId;
    private String transactionType;
    private int amount;
    private int balanceBefore;
    private int balanceAfter;
    private String description;
    private LocalDateTime createdAt;

    public Transaction() {
    }

    public Transaction(int userId, String transactionType, int amount, int balanceBefore, int balanceAfter, String description) {
        this.userId = userId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balanceBefore = balanceBefore;
        this.balanceAfter = balanceAfter;
        this.description = description;
    }

    public Transaction(int id, int userId, int relatedBetId, String transactionType, int amount, int balanceBefore, int balanceAfter, String description, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.relatedBetId = relatedBetId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balanceBefore = balanceBefore;
        this.balanceAfter = balanceAfter;
        this.description = description;
        this.createdAt = createdAt;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRelatedBetId() {
        return relatedBetId;
    }

    public void setRelatedBetId(int relatedBetId) {
        this.relatedBetId = relatedBetId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBalanceBefore() {
        return balanceBefore;
    }

    public void setBalanceBefore(int balanceBefore) {
        this.balanceBefore = balanceBefore;
    }

    public int getBalanceAfter() {
        return balanceAfter;
    }

    public void setBalanceAfter(int balanceAfter) {
        this.balanceAfter = balanceAfter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", userId=" + userId +
                ", relatedBetId=" + relatedBetId +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", balanceBefore=" + balanceBefore +
                ", balanceAfter=" + balanceAfter +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
