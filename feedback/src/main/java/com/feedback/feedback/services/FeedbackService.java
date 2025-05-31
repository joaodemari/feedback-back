package com.feedback.feedback.services;

import com.feedback.feedback.IdGenerator;
import com.feedback.feedback.dtos.FeedbackDTO;
import com.feedback.feedback.models.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.feedback.feedback.repositories.IFeedbackRepository;

import java.time.LocalDate;
import java.util.List;

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
        int n = IdGenerator.getNextId();
        while(feedbackRepository.findById(n).isPresent()){
            n = IdGenerator.getNextId();
        }
        feedback.setId(n);
        System.out.println(feedback.getId());
        feedback.setCreatedAt(LocalDate.now().toString());
        feedback.setMessage(feedbackDto.getMessage());
        feedback.setTopics(feedbackDto.getTopics());
        feedback.setFromMember(memberService.findById(feedbackDto.getIdFromMember()));
        feedback.setToMember(memberService.findById(feedbackDto.getIdToMember()));
        feedback.setAnonymous(feedbackDto.isAnonymous());
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getFeedbacks(){
        return feedbackRepository.findAll();
    }

}
