package com.att.tdp.bisbis10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.att.tdp.bisbis10.entities.Rating;

public interface RatingRepo extends JpaRepository<Rating, Long> {
	
}
