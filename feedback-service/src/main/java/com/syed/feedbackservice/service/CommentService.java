package com.syed.feedbackservice.service;

import com.syed.feedbackservice.dto.CommentDto;

public interface CommentService {

    CommentDto addCommentToFeedback(Long feedbackId, CommentDto commentDto);
}
