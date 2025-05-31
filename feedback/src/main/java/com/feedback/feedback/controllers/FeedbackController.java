package com.feedback.feedback.controllers;

import com.feedback.feedback.dtos.FeedbackDTO;
import com.feedback.feedback.models.Feedback;
import org.springframework.web.bind.annotation.*;
import com.feedback.feedback.services.FeedbackService;
import com.feedback.feedback.services.MemberService;

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

    @GetMapping("/")
    public String getFeedback() {

        return "Feedbacks populated successfully!";
    }

    @PostMapping("/")
    public Feedback submitFeedback(@RequestBody FeedbackDTO feedbackDto) {
        return feedbackService.createFeedback(feedbackDto);
    }
}
