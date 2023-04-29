package com.syed.feedbackservice.dto;

import com.syed.feedbackservice.enumeration.AreaEnum;

import java.time.LocalDateTime;
import java.util.List;

public class FeedbackDto {

    private Long id;
    private AreaEnum area;
    private String message;
    private String author;
    private String authorImg;
    private boolean resolved;
    private List<CommentDto> comments;
    private LocalDateTime dateTime;

    public FeedbackDto() {
    }

    public FeedbackDto(Long id, AreaEnum area, String message, String author, String authorImg, boolean resolved, List<CommentDto> comments, LocalDateTime dateTime) {
        this.id = id;
        this.area = area;
        this.message = message;
        this.author = author;
        this.authorImg = authorImg;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorImg() {
        return authorImg;
    }

    public void setAuthorImg(String authorImg) {
        this.authorImg = authorImg;
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
