package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.entities.Rating;
import com.att.tdp.bisbis10.entities.Restaurant;
import com.att.tdp.bisbis10.services.RestaurantService;

public class RatingDTO {
	private Integer restaurantId;
	private float rating;
	
	public Rating toRating(RestaurantService restaurantService) {
		Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
		return new Rating(restaurant, rating);
	}

	public RatingDTO(Integer restaurantId, float rating) {
		this.restaurantId = restaurantId;
		this.rating = rating;
	}
}