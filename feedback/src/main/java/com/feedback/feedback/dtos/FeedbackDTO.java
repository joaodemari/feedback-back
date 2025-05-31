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
    // setar hardcoded no front
    private int idFromMember;
    private int idToMember;
    // Adicionar ID no Enum
    private List<TopicsEnum> topics;

    private String message;
    // retirar porque é criado no backend
    private String createdAt;
}