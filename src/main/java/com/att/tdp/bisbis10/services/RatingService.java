package com.att.tdp.bisbis10.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.att.tdp.bisbis10.entities.*;
import com.att.tdp.bisbis10.repositories.RatingRepo;

@Service
public class RatingService {

	@Autowired
	private RatingRepo ratingRepo;

	public Rating createRating(Rating rating) {
		return ratingRepo.save(rating);
	}
}
