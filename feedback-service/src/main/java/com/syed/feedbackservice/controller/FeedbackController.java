package com.syed.feedbackservice.controller;

import com.syed.feedbackservice.dto.FeedbackDto;
import com.syed.feedbackservice.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class FeedbackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackController.class);

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    /**
     * endpoint to share feedback
     * @param feedbackDto the feedback containing area and comment
     * @return string method that feedback has been shared
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/feedback")
    public String postFeedback(@RequestBody FeedbackDto feedbackDto) {
        LOGGER.info("Entering FeedbackController:postFeedback");
        return feedbackService.postFeedback(feedbackDto);
    }

    /**
     * endpoint to get map containing feedback
     * @return feedback as map
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/feedback")
    public Map<String, List<FeedbackDto>> getFeedback() {
        LOGGER.info("Entering FeedbackController:getFeedback");
        return feedbackService.getFeedback();
    }
}
