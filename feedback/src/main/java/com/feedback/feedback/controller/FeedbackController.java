package com.feedback.feedback.controller;
import com.feedback.feedback.dtos.FeedbackDTO;
import com.feedback.feedback.models.Feedback;
import org.springframework.web.bind.annotation.*;
import com.feedback.feedback.services.FeedbackService;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

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
