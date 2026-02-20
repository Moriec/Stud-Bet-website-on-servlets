package com.studbet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "betting_events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BettingEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private int id;
    @Column(name = "target_user_id")
    private int targetUserId;
    @Column(name = "subject_id")
    private int subjectId;
    @Column(name = "semester")
    private int semestr;
    @Column(name = "academic_year")
    private String academicYear;
    @Column(name = "event_type")
    private String eventType;
    @Column(name = "status")
    private String status;
    @Column(name = "description")
    private String description;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "close_it")
    private LocalDateTime closedIt;

    public BettingEvent(int targetUserId, int subjectId, int semestr, String academicYear, String eventType, String status, String description) {
        this.targetUserId = targetUserId;
        this.subjectId = subjectId;
        this.semestr = semestr;
        this.academicYear = academicYear;
        this.eventType = eventType;
        this.status = status;
        this.description = description;
    }
}