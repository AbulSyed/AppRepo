package com.syed.feedbackservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syed.feedbackservice.dto.FeedbackDto;
import com.syed.feedbackservice.entity.Feedback;
import com.syed.feedbackservice.repository.FeedbackRepository;
import com.syed.feedbackservice.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackServiceImpl.class);

    private final FeedbackRepository feedbackRepository;
    private final ObjectMapper mapper;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, ObjectMapper mapper) {
        this.feedbackRepository = feedbackRepository;
        this.mapper = mapper;
    }

    @Override
    public String postFeedback(FeedbackDto feedbackDto) {
        LOGGER.info("Entering FeedbackServiceImpl:postFeedback");

        Feedback feedback = mapper.convertValue(feedbackDto, Feedback.class);

        feedbackRepository.save(feedback);

        // sending string as we don't need to send object back to client
        // users will not see their submitted feedback, only admins will...
        return "Feedback shared";
    }

    @Override
    public Map<String, List<FeedbackDto>> getFeedback() {
        LOGGER.info("Entering FeedbackServiceImpl:getFeedback");

        List<Feedback> feedbacks = feedbackRepository.findAll();
        List<FeedbackDto> feedbackDtos = new ArrayList<>();

        for (Feedback feedback : feedbacks) {
            FeedbackDto feedbackDto = mapper.convertValue(feedback, FeedbackDto.class);
            feedbackDtos.add(feedbackDto);
        }

        return getFeedbackMap(feedbackDtos);
    }

    private Map<String, List<FeedbackDto>> getFeedbackMap(List<FeedbackDto> feedbackDtos) {
        Map<String, List<FeedbackDto>> map = new HashMap<>();

        for (FeedbackDto feedbackDto : feedbackDtos) {
            String tempKey = feedbackDto.getArea().toString();

            if (!map.containsKey(tempKey)) {
                map.put(tempKey, new ArrayList<>());
            }
            map.get(tempKey).add(feedbackDto);
        }

        return map;
    }
}
