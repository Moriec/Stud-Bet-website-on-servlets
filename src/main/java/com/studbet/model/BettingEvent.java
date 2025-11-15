package com.studbet.model;

import java.time.LocalDateTime;

public class BettingEvent {
    private int id;
    private int targetUserId;
    private int subjectId;
    private int semestr;
    private String academicYear;
    private String eventType;
    private String Status;
    private LocalDateTime createdAt;
    private LocalDateTime closedIt;

    public BettingEvent() {
    }

    public BettingEvent(int targetUserId, int subjectId, int semestr, String academicYear, String eventType, String status) {
        this.targetUserId = targetUserId;
        this.subjectId = subjectId;
        this.semestr = semestr;
        this.academicYear = academicYear;
        this.eventType = eventType;
        Status = status;
    }

    public BettingEvent(int id, int targetUserId, int subjectId, int semestr, String academicYear, String eventType, String status, LocalDateTime createdAt, LocalDateTime closedIt) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(int targetUserId) {
        this.targetUserId = targetUserId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getSemestr() {
        return semestr;
    }

    public void setSemestr(int semestr) {
        this.semestr = semestr;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getClosedIt() {
        return closedIt;
    }

    public void setClosedIt(LocalDateTime closedIt) {
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
