package com.syed.feedbackservice.repository;

import com.syed.feedbackservice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
