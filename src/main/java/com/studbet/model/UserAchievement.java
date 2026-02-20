package com.studbet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_achievements")
@IdClass(UserAchievementId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAchievement {
    @Id
    @Column(name = "user_id")
    private int userId;
    @Id
    @Column(name = "achievements_id")
    private int achievementId;
    @Column(name = "earned_at")
    private LocalDateTime earnedAt;
}