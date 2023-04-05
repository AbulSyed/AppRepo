package com.syed.feedbackservice.entity;

import com.syed.feedbackservice.enumeration.AreaEnum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private AreaEnum area;
    private String comment;

    public Feedback() {
    }

    public Feedback(Long id, AreaEnum area, String comment) {
        this.id = id;
        this.area = area;
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", area=" + area +
                ", comment='" + comment + '\'' +
                '}';
    }
}
