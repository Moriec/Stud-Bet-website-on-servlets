package com.studbet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student_result")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private int resultId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "subject_id")
    private int subjectId;
    @Column(name = "actual_score")
    private int actualScore;
    @Column(name = "semester")
    private int semestr;
    @Column(name = "academic_year")
    private String academicYear;
    @Column(name = "is_finelized")
    private boolean isFinelized;

    public StudentResult(int userId, int subjectId, int actualScore, int semestr, String academicYear, boolean isFinelized) {
        this.userId = userId;
        this.subjectId = subjectId;
        this.actualScore = actualScore;
        this.semestr = semestr;
        this.academicYear = academicYear;
        this.isFinelized = isFinelized;
    }
}