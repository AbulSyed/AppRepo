package com.syed.feedbackservice.entity;

import com.syed.feedbackservice.enumeration.AreaEnum;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private AreaEnum area;
    private String message;
    private boolean resolved;
    @OneToMany(mappedBy = "feedback", cascade = CascadeType.ALL)
    private List<Comment> comments;
    private LocalDateTime dateTime;

    public Feedback() {
    }

    public Feedback(Long id, AreaEnum area, String message, boolean resolved, List<Comment> comments, LocalDateTime dateTime) {
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
