package com.reanima.business.repository;

import com.reanima.business.repository.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("SELECT u FROM UserEntity u WHERE u.userEmail = ?1")
    UserEntity findById(String userEmail);
}
