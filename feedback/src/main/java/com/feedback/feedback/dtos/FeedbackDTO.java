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
    private int id;
    private int idFromMember;
    private int idToMember;
    private List<TopicsEnum> topics;
    private String message;
    private String createdAt;
}