package com.feedback.feedback.dtos;

import java.util.List;

import com.feedback.feedback.models.TopicsEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeedbackDTO {
    // setar hardcoded no front
    private int idFromMember;
    private int idToMember;
    // Adicionar ID no Enum
    private List<TopicsEnum> topics;
    private String message;
    private boolean anonymous;

    public void setIdFromMember(int idFromMember) {
        this.idFromMember = idFromMember;
    }

    public void setIdToMember(int idToMember) {
        this.idToMember = idToMember;
    }

    public void setTopics(List<TopicsEnum> topics) {
        this.topics = topics;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIdFromMember() {
        return idFromMember;
    }

    public int getIdToMember() {
        return idToMember;
    }

    public List<TopicsEnum> getTopics() {
        return topics;
    }

    public String getMessage() {
        return message;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }
}