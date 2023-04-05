package com.syed.feedbackservice.repository;

import com.syed.feedbackservice.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
