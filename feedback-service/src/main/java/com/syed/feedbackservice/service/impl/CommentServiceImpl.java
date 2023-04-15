package com.syed.feedbackservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syed.feedbackservice.dto.CommentDto;
import com.syed.feedbackservice.entity.Comment;
import com.syed.feedbackservice.entity.Feedback;
import com.syed.feedbackservice.exception.NotFoundException;
import com.syed.feedbackservice.repository.CommentRepository;
import com.syed.feedbackservice.repository.FeedbackRepository;
import com.syed.feedbackservice.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final CommentRepository commentRepository;
    private final FeedbackRepository feedbackRepository;
    private final ObjectMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, FeedbackRepository feedbackRepository, ObjectMapper mapper) {
        this.commentRepository = commentRepository;
        this.feedbackRepository = feedbackRepository;
        this.mapper = mapper;
    }

    /**
     * method to add a comment to feedback
     * @param feedbackId the feedback identifier
     * @param commentDto the comment to add
     * @return comment object
     */
    @Override
    public CommentDto addCommentToFeedback(Long feedbackId, CommentDto commentDto) {
        LOGGER.info("Entering CommentServiceImpl:addCommentToFeedback");

        Feedback feedback = feedbackRepository.findById(feedbackId).orElseThrow(
                () -> new NotFoundException("Feedback with id " + feedbackId + " not found"));

        Comment comment = mapper.convertValue(commentDto, Comment.class);

        comment.setFeedback(feedback);
        comment.setDateTime(LocalDateTime.now());
        return mapper.convertValue(commentRepository.save(comment), CommentDto.class);
    }
}
