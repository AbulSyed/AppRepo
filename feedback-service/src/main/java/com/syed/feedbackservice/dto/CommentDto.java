package com.syed.feedbackservice.dto;

import com.syed.feedbackservice.entity.Feedback;

import java.time.LocalDateTime;

public class CommentDto {

    private Long id;
    private String author;
    private String message;
//    private Feedback feedback;
    private LocalDateTime dateTime;

    public CommentDto() {
    }

    public CommentDto(Long id, String author, String message, LocalDateTime dateTime) {
        this.id = id;
        this.author = author;
        this.message = message;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
