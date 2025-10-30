package com.studbet.model;

public class StudentResult {
    private int resultId;
    private int userId;
    private int subjectId;
    private int actualScore;
    private int semestr;
    private String academicYear;
    private boolean isFinelized;

    public StudentResult() {
    }

    public StudentResult(int resultId, int userId, int subjectId, int actualScore, int semestr, String academicYear, boolean isFinelized) {
        this.resultId = resultId;
        this.userId = userId;
        this.subjectId = subjectId;
        this.actualScore = actualScore;
        this.semestr = semestr;
        this.academicYear = academicYear;
        this.isFinelized = isFinelized;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getActualScore() {
        return actualScore;
    }

    public void setActualScore(int actualScore) {
        this.actualScore = actualScore;
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

    public boolean isFinelized() {
        return isFinelized;
    }

    public void setFinelized(boolean finelized) {
        isFinelized = finelized;
    }

    @Override
    public String toString() {
        return "StudentResult{" +
                "resultId=" + resultId +
                ", userId=" + userId +
                ", subjectId=" + subjectId +
                ", actualScore=" + actualScore +
                ", semestr=" + semestr +
                ", academicYear='" + academicYear + '\'' +
                ", isFinelized=" + isFinelized +
                '}';
    }
}
