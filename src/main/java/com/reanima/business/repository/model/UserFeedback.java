package com.reanima.business.repository.model;

import lombok.*;

import javax.persistence.*;

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
}
