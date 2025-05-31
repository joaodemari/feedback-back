package com.feedback.feedback.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Feedback {

    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "from_member_id")
    private Member fromMember;

    @ManyToOne
    @JoinColumn(name = "to_member_id")
    private Member toMember;

    @ElementCollection(targetClass = TopicsEnum.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "feedback_topics",
            joinColumns = @JoinColumn(name = "feedback_id")
    )
    @Column(name = "topic")
    private List<TopicsEnum> topics;
    private String message;
    private String createdAt;
    private boolean anonymous;

    public Feedback(int id, Member fromMember, Member toMember, List<TopicsEnum> topics, String message,
                    String createdAt) {
        this.id = id;
        this.fromMember = fromMember;
        this.toMember = toMember;
        this.topics = topics;
        this.message = message;
        this.createdAt = createdAt;
    }

    public Feedback() {}

    public void setId(int id) {
        this.id = id;
    }

    public void setFromMember(Member fromMember) {
        this.fromMember = fromMember;
    }

    public void setToMember(Member toMember) {
        this.toMember = toMember;
    }

    public void setTopics(List<TopicsEnum> topics) {
        this.topics = topics;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getMessage() {
        return message;
    }

    public List<TopicsEnum> getTopics() {
        return topics;
    }

    public Member getToMember() {
        return toMember;
    }

    public Member getFromMember() {
        return fromMember;
    }

    public int getId() {
        return id;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }
}
