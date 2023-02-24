package com.reanima.business.repository;

import com.reanima.business.repository.model.UserFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFeedbackRepository extends JpaRepository<UserFeedback, Integer> {
}
