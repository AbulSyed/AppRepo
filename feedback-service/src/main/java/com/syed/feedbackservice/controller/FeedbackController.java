package com.syed.feedbackservice.controller;

import com.syed.feedbackservice.dto.FeedbackDto;
import com.syed.feedbackservice.service.FeedbackService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/feedback")
    public String postFeedback(@RequestBody FeedbackDto feedbackDto) {
        System.out.println(feedbackDto);
        return feedbackService.postFeedback(feedbackDto);
    }
}
