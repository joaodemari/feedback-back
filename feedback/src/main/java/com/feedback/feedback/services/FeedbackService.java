package com.feedback.feedback.services;

import com.feedback.feedback.dtos.FeedbackDTO;
import com.feedback.feedback.models.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.feedback.feedback.repositories.IFeedbackRepository;

@Service
public class FeedbackService {
    private final IFeedbackRepository feedbackRepository;

    @Autowired
    private MemberService memberService;

    public FeedbackService(IFeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public Feedback createFeedback(FeedbackDTO feedbackDto) {
        Feedback feedback = new Feedback();
        feedback.setId(feedbackDto.getId());
        feedback.setCreatedAt(feedbackDto.getCreatedAt());
        feedback.setMessage(feedbackDto.getMessage());
        feedback.setTopics(feedbackDto.getTopics());
        feedback.setFromMember(memberService.findById(feedbackDto.getIdFromMember()));
        feedback.setToMember(memberService.findById(feedbackDto.getIdToMember()));
        return feedbackRepository.save(feedback);
    }
}
