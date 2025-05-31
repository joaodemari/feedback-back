package com.feedback.feedback.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TopicsEnum {
    // trocar topicos pelos tópicos reais
    WORK_QUALITY("Work Quality"),
    COMMUNICATION("Communication"),
    TEAMWORK("Teamwork"),
    PUNCTUALITY("Punctuality"),
    INITIATIVE("Initiative"),
    PROBLEM_SOLVING("Problem Solving"),
    ADAPTABILITY("Adaptability"),
    LEADERSHIP("Leadership");

    private final String topic;


    TopicsEnum(String topic) {
        this.topic = topic;
    }



    public String getTopic() {
        return topic;
    }
}
