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
}
