package com.syed.feedbackservice.dto;

import com.syed.feedbackservice.enumeration.AreaEnum;

import java.time.LocalDateTime;
import java.util.List;

public class FeedbackDto {

    private Long id;
    private AreaEnum area;
    private String message;
    private boolean resolved;
    private List<CommentDto> comments;
    private LocalDateTime dateTime;

    public FeedbackDto() {
    }

    public FeedbackDto(Long id, AreaEnum area, String message, boolean resolved, List<CommentDto> comments, LocalDateTime dateTime) {
        this.id = id;
        this.area = area;
        this.message = message;
        this.resolved = resolved;
        this.comments = comments;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AreaEnum getArea() {
        return area;
    }

    public void setArea(AreaEnum area) {
        this.area = area;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
