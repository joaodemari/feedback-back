package com.feedback.feedback.models;

public enum TopicsEnum {
    WORK_QUALITY("Work Quality", 1),
    COMMUNICATION("Communication", 1),
    TEAMWORK("Teamwork", 1),
    PUNCTUALITY("Punctuality", 1),
    INITIATIVE("Initiative", 1),
    PROBLEM_SOLVING("Problem Solving", 1),
    ADAPTABILITY("Adaptability", 1),
    LEADERSHIP("Leadership", 1),;

    private final String topic;
    private final int points;

    TopicsEnum(String topic, int points) {
        this.topic = topic;
        this.points = points;
    }

    public static TopicsEnum fromId(int id) {
        for (TopicsEnum topic : TopicsEnum.values()) {
            if (topic.ordinal() == id) {
                return topic;
            }
        }
        throw new IllegalArgumentException("Invalid topic ID: " + id);
    }

    public String getTopic() {
        return topic;
    }

    public int getPoints() {
        return points;
    }
}
