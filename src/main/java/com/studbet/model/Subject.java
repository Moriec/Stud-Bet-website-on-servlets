package com.studbet.model;

public class Subject {
    private int id;
    private String name;
    private String code;
    private String description;
    private int maxScore;

    public Subject(String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
    }

    public Subject(String name, String code, String description, int maxScore) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.maxScore = maxScore;
    }

    public Subject(int id, String name, String code, String description, int maxScore) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.maxScore = maxScore;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", maxScore=" + maxScore +
                '}';
    }
}
