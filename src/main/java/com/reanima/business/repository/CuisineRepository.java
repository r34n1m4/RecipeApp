package com.reanima.business.repository;

import com.reanima.business.repository.model.CuisineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuisineRepository extends JpaRepository<CuisineEntity, Integer> {
}
