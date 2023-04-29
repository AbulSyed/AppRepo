package com.syed.feedbackservice.controller;

import com.syed.feedbackservice.dto.FeedbackDto;
import com.syed.feedbackservice.service.FeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value = "Feedback controller exposes endpoints: shareFeedback, getFeedback, feedback")
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
    @ApiOperation(value = "Endpoint to share feedback")
    @ApiResponses({
            @ApiResponse(
                    code = 201,
                    message = "CREATED"
            )
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/shareFeedback")
    public String postFeedback(@RequestBody FeedbackDto feedbackDto) {
        LOGGER.info("Entering FeedbackController:postFeedback");

        return feedbackService.postFeedback(feedbackDto);
    }

    /**
     * endpoint to get map containing feedback
     * @return feedback as map
     */
    @ApiOperation(value = "Endpoint to get map containing feedback")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "OK",
                    response = Map.class,
                    responseContainer = "List"
            )
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getFeedback")
    public Map<String, List<FeedbackDto>> getFeedback() {
        LOGGER.info("Entering FeedbackController:getFeedback");

        return feedbackService.getFeedback();
    }

    /**
     * endpoint which allows admins to update 'resolved' status
     * @param feedbackId the feedback identifier
     * @param feedbackDto the update object
     * @return the updated feedback object
     */
    @ApiOperation(value = "Endpoint which allows admins to update 'resolved' status")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "OK",
                    response = FeedbackDto.class
            ),
            @ApiResponse(
                    code = 404,
                    message = "Feedback not found"
            )
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/feedback/{feedbackId}")
    public FeedbackDto updateFeedback(@PathVariable Long feedbackId, @RequestBody FeedbackDto feedbackDto) {
        LOGGER.info("Entering FeedbackController:updateFeedback");

        return feedbackService.updateFeedback(feedbackId, feedbackDto.isResolved());
    }
}
