package com.syed.feedbackservice.service;

import com.syed.feedbackservice.dto.FeedbackDto;

public interface FeedbackService {

    String postFeedback(FeedbackDto feedbackDto);
}
