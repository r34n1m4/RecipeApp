package com.reanima.business.repository;

import com.reanima.business.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.userEmail = ?1")
    User findByEmail(String userEmail);
}
