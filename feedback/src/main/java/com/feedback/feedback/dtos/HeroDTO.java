package com.feedback.feedback.dtos;

import lombok.Data;

@Data
public class HeroDTO {
    private String name;
    private String photoUrl;
    private int totalLikes;

    public HeroDTO(String name, String photoUrl, int totalLikes) {
        this.name = name;
        this.photoUrl = photoUrl;
        this.totalLikes = totalLikes;
    }
}
