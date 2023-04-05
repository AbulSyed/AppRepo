package com.syed.feedbackservice.dto;

import com.syed.feedbackservice.enumeration.AreaEnum;

public class FeedbackDto {

    private AreaEnum area;
    private String comment;

    public FeedbackDto() {
    }

    public FeedbackDto(AreaEnum area, String comment) {
        this.area = area;
        this.comment = comment;
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
        return "FeedbackDto{" +
                "area=" + area +
                ", comment='" + comment + '\'' +
                '}';
    }
}
