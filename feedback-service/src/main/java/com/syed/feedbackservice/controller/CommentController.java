package com.syed.feedbackservice.controller;

import com.syed.feedbackservice.dto.CommentDto;
import com.syed.feedbackservice.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(value = "Comment controller exposes endpoints: addComment")
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
    @ApiOperation(value = "Endpoint to allow admins to add a comment to a feedback")
    @ApiResponses({
            @ApiResponse(
                    code = 201,
                    message = "CREATED"
            )
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addComment/{feedbackId}")
    public CommentDto addCommentToFeedback(@PathVariable Long feedbackId, @RequestBody CommentDto commentDto) {
        LOGGER.info("Entering CommentController:addCommentToFeedback");

        return commentService.addCommentToFeedback(feedbackId, commentDto);
    }
}
