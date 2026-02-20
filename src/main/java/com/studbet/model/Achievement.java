package com.studbet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "achievements")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "achievements_id")
    private long id;
    @Column(name = "achevements_name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "icon_url")
    private String icon_url;
    @Column(name = "points_reward")
    private int pointsReward;
    @Column(name = "achievement_type")
    private String achievementType;
    @Column(name = "criteria_json")
    private String criteriaJson;

    public Achievement(String name, String description, String icon_url, int pointsReward, String achievementType, String criteriaJson) {
        this.name = name;
        this.description = description;
        this.icon_url = icon_url;
        this.pointsReward = pointsReward;
        this.achievementType = achievementType;
        this.criteriaJson = criteriaJson;
    }
}