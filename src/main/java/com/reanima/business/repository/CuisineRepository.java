package com.reanima.business.repository;

import com.reanima.business.repository.model.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuisineRepository extends JpaRepository<Cuisine, Integer> {
}
