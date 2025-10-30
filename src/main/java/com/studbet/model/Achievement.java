package com.studbet.model;

public class Achievement {
    int id;
    String name;
    String description;
    String icon_url;
    int pointsReward;
    String achievementType;
    String criteriaJson;

    public Achievement() {
    }

    public Achievement(String name, String description, String icon_url, int pointsReward, String achievementType, String criteriaJson) {
        this.name = name;
        this.description = description;
        this.icon_url = icon_url;
        this.pointsReward = pointsReward;
        this.achievementType = achievementType;
        this.criteriaJson = criteriaJson;
    }

    public Achievement(int id, String name, String description, String icon_url, int pointsReward, String achievementType, String criteriaJson) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.icon_url = icon_url;
        this.pointsReward = pointsReward;
        this.achievementType = achievementType;
        this.criteriaJson = criteriaJson;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public int getPointsReward() {
        return pointsReward;
    }

    public void setPointsReward(int pointsReward) {
        this.pointsReward = pointsReward;
    }

    public String getAchievementType() {
        return achievementType;
    }

    public void setAchievementType(String achievementType) {
        this.achievementType = achievementType;
    }

    public String getCriteriaJson() {
        return criteriaJson;
    }

    public void setCriteriaJson(String criteriaJson) {
        this.criteriaJson = criteriaJson;
    }

    @Override
    public String toString() {
        return "Achievement{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", icon_url='" + icon_url + '\'' +
                ", pointsReward=" + pointsReward +
                ", achievementType='" + achievementType + '\'' +
                ", criteriaJson='" + criteriaJson + '\'' +
                '}';
    }
}
