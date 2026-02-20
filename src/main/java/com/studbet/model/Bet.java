package com.studbet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "bets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bet_id")
    private Long id;
    @Column(name = "bettor_id")
    private int bettorId;
    @Column(name = "event_id")
    private int eventId;
    @Column(name = "bet_amount")
    private int betAmount;
    @Column(name = "status")
    private String status;
    @Column(name = "payout_amount")
    private int payoutAmount;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt;
    @Column(name = "predicted_score_min")
    private int predictedScoreMin;
    @Column(name = "predicted_score_max")
    private int predictedScoreMax;

    public Bet(int bettorId, int eventId, int betAmount, String status, int payoutAmount, int predictedScoreMin, int predictedScoreMax) {
        this.bettorId = bettorId;
        this.eventId = eventId;
        this.betAmount = betAmount;
        this.status = status;
        this.payoutAmount = payoutAmount;
        this.predictedScoreMin = predictedScoreMin;
        this.predictedScoreMax = predictedScoreMax;
    }
}