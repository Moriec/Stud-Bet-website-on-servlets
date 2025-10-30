package com.studbet.model;

import java.time.LocalDateTime;

public class Betting_Event {
    private int id;
    private int targetUserId;
    private int subjectId;
    private int semestr;
    private String academicYear;
    private String eventType;
    private String Status;
    private LocalDateTime createdAt;
    private LocalDateTime closedIt;

    public Betting_Event() {
    }

    public Betting_Event(int targetUserId, int subjectId, int semestr, String academicYear, String eventType, String status) {
        this.targetUserId = targetUserId;
        this.subjectId = subjectId;
        this.semestr = semestr;
        this.academicYear = academicYear;
        this.eventType = eventType;
        Status = status;
    }

    public Betting_Event(int id, int targetUserId, int subjectId, int semestr, String academicYear, String eventType, String status, LocalDateTime createdAt, LocalDateTime closedIt) {
        this.id = id;
        this.targetUserId = targetUserId;
        this.subjectId = subjectId;
        this.semestr = semestr;
        this.academicYear = academicYear;
        this.eventType = eventType;
        Status = status;
        this.createdAt = createdAt;
        this.closedIt = closedIt;
    }

    @Override
    public String toString() {
        return "Betting_Event{" +
                "id=" + id +
                ", targetUserId=" + targetUserId +
                ", subjectId=" + subjectId +
                ", semestr=" + semestr +
                ", academicYear='" + academicYear + '\'' +
                ", eventType='" + eventType + '\'' +
                ", Status='" + Status + '\'' +
                ", createdAt=" + createdAt +
                ", closedIt=" + closedIt +
                '}';
    }
}
