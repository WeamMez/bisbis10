package com.att.tdp.bisbis10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.att.tdp.bisbis10.services.RatingService;
import com.att.tdp.bisbis10.services.RestaurantService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	
	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@PostMapping
	public ResponseEntity<Void> addRestaurantRating(@RequestBody RatingDTO ratingDto) {
		ratingService.createRating(ratingDto.toRating(restaurantService));
		return ResponseEntity.ok().build();
	}
	
}
