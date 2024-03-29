package com.syed.feedbackservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "message", nullable = false)
    private String message;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "feedback_id")
    @JsonIgnore
    private Feedback feedback;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    public Comment() {
    }

    public Comment(Long id, String author, String message, Feedback feedback, LocalDateTime dateTime) {
        this.id = id;
        this.author = author;
        this.message = message;
        this.feedback = feedback;
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

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
