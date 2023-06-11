package com.syed.feedbackservice.entity;

import com.syed.feedbackservice.enumeration.AreaEnum;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated
    private AreaEnum area;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "author_img")
    private String authorImg;

    @Column(name = "resolved")
    private boolean resolved;

    @OneToMany(mappedBy = "feedback", cascade = CascadeType.ALL)
    private List<Comment> comments;
    private LocalDateTime dateTime;

    public Feedback() {
    }

    public Feedback(Long id, AreaEnum area, String message, String author, String authorImg, boolean resolved, List<Comment> comments, LocalDateTime dateTime) {
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
