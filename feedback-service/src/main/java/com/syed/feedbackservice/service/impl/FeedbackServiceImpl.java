package com.syed.feedbackservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syed.feedbackservice.dto.FeedbackDto;
import com.syed.feedbackservice.entity.Feedback;
import com.syed.feedbackservice.repository.FeedbackRepository;
import com.syed.feedbackservice.service.FeedbackService;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ObjectMapper mapper;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, ObjectMapper mapper) {
        this.feedbackRepository = feedbackRepository;
        this.mapper = mapper;
    }

    @Override
    public String postFeedback(FeedbackDto feedbackDto) {
        Feedback feedback = mapper.convertValue(feedbackDto, Feedback.class);

        feedbackRepository.save(feedback);

        // sending string as we don't need to send object back to client
        // users will not see their submitted feedback, only admins will...
        return "Feedback shared";
    }
}
