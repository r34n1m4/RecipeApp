package com.reanima.business.repository.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_feedback")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_feedback_id")
    private int userFeedbackId;

    @Column(name="user_comment")
    private String userComment;

    @Column(name="user_rating")
    private int userRating;

    @CreationTimestamp
    @Column(name = "feedback_created")
    private LocalDateTime feedbackCreated;
}
