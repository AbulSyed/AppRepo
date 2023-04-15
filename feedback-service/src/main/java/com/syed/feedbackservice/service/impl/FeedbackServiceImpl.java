package com.syed.feedbackservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syed.feedbackservice.dto.FeedbackDto;
import com.syed.feedbackservice.entity.Feedback;
import com.syed.feedbackservice.exception.NotFoundException;
import com.syed.feedbackservice.repository.FeedbackRepository;
import com.syed.feedbackservice.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackServiceImpl.class);

    private final FeedbackRepository feedbackRepository;
    private final ObjectMapper mapper;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, ObjectMapper mapper) {
        this.feedbackRepository = feedbackRepository;
        this.mapper = mapper;
    }

    /**
     * endpoint which allows users to share feedback
     * @param feedbackDto the feedback object
     * @return a string message
     */
    @Override
    public String postFeedback(FeedbackDto feedbackDto) {
        LOGGER.info("Entering FeedbackServiceImpl:postFeedback");

        Feedback feedback = mapper.convertValue(feedbackDto, Feedback.class);
        feedback.setDateTime(LocalDateTime.now());

        feedbackRepository.save(feedback);

        // sending string as we don't need to send object back to client
        // users will not see their submitted feedback, only admins will...
        return "Feedback shared";
    }

    /**
     * endpoint which retrieves all feedback
     * @return hashmap containing all feedback
     */
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

    /**
     * endpoint which updates the resolved boolean of a feedback
     * @param feedbackId the feedback identifier
     * @param status the boolean resovled status
     * @return updated feedback object
     */
    @Override
    public FeedbackDto updateFeedback(Long feedbackId, boolean status) {
        LOGGER.info("Entering FeedbackServiceImpl:updateResolved");

        Feedback feedback = feedbackRepository.findById(feedbackId).orElseThrow(
                () -> new NotFoundException("Feedback with id " + feedbackId + " not found"));

        feedback.setResolved(status);
        return mapper.convertValue(feedbackRepository.save(feedback), FeedbackDto.class);
    }

    /**
     * utility method to convert a list of feedback to hashmap
     * @param feedbackDtos list of feedback objects
     * @return hashmap of feedbacks
     */
    private Map<String, List<FeedbackDto>> getFeedbackMap(List<FeedbackDto> feedbackDtos) {
        LOGGER.info("Entering FeedbackServiceImpl:getFeedbackMap");

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
