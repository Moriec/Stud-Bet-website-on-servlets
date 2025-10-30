package com.studbet.model;

import java.time.LocalDateTime;

public class Bet {
    private int id;
    private int bettorId;
    private int eventId;
    private int betAmount;
    private String status;
    private int payoutAmount;
    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;
    private int predictedScoreMin;
    private int predictedScoreMax;

    public Bet() {
    }

    public Bet(int bettorId, int eventId, int betAmount, String status, int payoutAmount, int predictedScoreMin, int predictedScoreMax) {
        this.bettorId = bettorId;
        this.eventId = eventId;
        this.betAmount = betAmount;
        this.status = status;
        this.payoutAmount = payoutAmount;
        this.predictedScoreMin = predictedScoreMin;
        this.predictedScoreMax = predictedScoreMax;
    }

    public Bet(int id, int bettorId, int eventId, int betAmount, String status, int payoutAmount, LocalDateTime createdAt, LocalDateTime resolvedAt, int predictedScoreMin, int predictedScoreMax) {
        this.id = id;
        this.bettorId = bettorId;
        this.eventId = eventId;
        this.betAmount = betAmount;
        this.status = status;
        this.payoutAmount = payoutAmount;
        this.createdAt = createdAt;
        this.resolvedAt = resolvedAt;
        this.predictedScoreMin = predictedScoreMin;
        this.predictedScoreMax = predictedScoreMax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBettorId() {
        return bettorId;
    }

    public void setBettorId(int bettorId) {
        this.bettorId = bettorId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPayoutAmount() {
        return payoutAmount;
    }

    public void setPayoutAmount(int payoutAmount) {
        this.payoutAmount = payoutAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getResolvedAt() {
        return resolvedAt;
    }

    public void setResolvedAt(LocalDateTime resolvedAt) {
        this.resolvedAt = resolvedAt;
    }

    public int getPredictedScoreMin() {
        return predictedScoreMin;
    }

    public void setPredictedScoreMin(int predictedScoreMin) {
        this.predictedScoreMin = predictedScoreMin;
    }

    public int getPredictedScoreMax() {
        return predictedScoreMax;
    }

    public void setPredictedScoreMax(int predictedScoreMax) {
        this.predictedScoreMax = predictedScoreMax;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "id=" + id +
                ", bettorId=" + bettorId +
                ", eventId=" + eventId +
                ", betAmount=" + betAmount +
                ", status='" + status + '\'' +
                ", payoutAmount=" + payoutAmount +
                ", createdAt=" + createdAt +
                ", resolvedAt=" + resolvedAt +
                ", predictedScoreMin=" + predictedScoreMin +
                ", predictedScoreMax=" + predictedScoreMax +
                '}';
    }
}
