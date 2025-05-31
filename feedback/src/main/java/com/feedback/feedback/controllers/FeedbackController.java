package com.feedback.feedback.controllers;

import com.feedback.feedback.dtos.FeedbackDTO;
import com.feedback.feedback.models.Feedback;
import org.springframework.web.bind.annotation.*;
import com.feedback.feedback.services.FeedbackService;
import com.feedback.feedback.services.MemberService;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    private final MemberService memberService;

    public FeedbackController(FeedbackService feedbackService, MemberService memberService) {
        this.memberService = memberService;
        this.feedbackService = feedbackService;
        memberService.populateMembers();
        feedbackService.populateFeedbacks();
    }

     @CrossOrigin(origins = "*")
    @GetMapping("/")
    public List<Feedback> getFeedback() {
        return feedbackService.getFeedbacks();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/")
    public Feedback submitFeedback(@RequestBody FeedbackDTO feedbackDto) {
        return feedbackService.createFeedback(feedbackDto);
    }
}
