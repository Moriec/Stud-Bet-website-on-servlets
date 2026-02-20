package com.studbet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subjects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private int id;
    @Column(name = "subject_name")
    private String name;
    @Column(name = "subject_code")
    private String code;
    @Column(name = "description")
    private String description;
    @Column(name = "max_score")
    private int maxScore;

    public Subject(String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.maxScore = 100;
    }

    public Subject(String name, String code, String description, int maxScore) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.maxScore = maxScore;
    }
}