package com.syed.feedbackservice.controller;

import com.syed.feedbackservice.dto.CommentDto;
import com.syed.feedbackservice.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * endpoint to allow admins to add a comment to a feedback
     * @param feedbackId the feedback identifier
     * @param commentDto the comment to add
     * @return the comment object
     */
    @PostMapping("/addComment/{feedbackId}")
    public CommentDto addCommentToFeedback(@PathVariable Long feedbackId, @RequestBody CommentDto commentDto) {
        LOGGER.info("Entering CommentController:addCommentToFeedback");

        return commentService.addCommentToFeedback(feedbackId, commentDto);
    }
}
