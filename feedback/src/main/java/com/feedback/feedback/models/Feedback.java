package com.feedback.feedback.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Feedback {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "from_member_id")
    private Member fromMember;

    @ManyToOne
    @JoinColumn(name = "to_member_id")
    private Member toMember;

    private TopicsEnum[] topics;
    private String message;
    private String createdAt;

    public Feedback(String id, Member fromMember, Member toMember, TopicsEnum[] topics, String message,
            String createdAt) {
        this.id = id;
        this.fromMember = fromMember;
        this.toMember = toMember;
        this.topics = topics;
        this.message = message;
        this.createdAt = createdAt;
    }
}