package com.feedback.feedback.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Member {

    @Id
    private String id;
    private String name;
    private String photoUrl;

    public Member(String id, String name, String photoUrl) {
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
    }
}
