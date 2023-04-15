package com.syed.feedbackservice.service;

import com.syed.feedbackservice.dto.FeedbackDto;

import java.util.List;
import java.util.Map;

public interface FeedbackService {

    String postFeedback(FeedbackDto feedbackDto);
    Map<String, List<FeedbackDto>> getFeedback();
    FeedbackDto updateFeedback(Long feedbackId, boolean status);
}
