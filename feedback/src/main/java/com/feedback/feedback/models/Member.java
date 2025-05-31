package com.feedback.feedback.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Member {

    @Id
    private int id;

    @OneToMany(mappedBy = "toMember")
    List<Feedback> feedbacksToThisMember;

    private String name;
    private String photoUrl;

    public Member(int id, String name, String photoUrl) {
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
    }

    public Member() {

    }
}
