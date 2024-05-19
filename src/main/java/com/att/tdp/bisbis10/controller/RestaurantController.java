package com.att.tdp.bisbis10.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.att.tdp.bisbis10.entities.Restaurant;
import com.att.tdp.bisbis10.services.RestaurantService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}

	@GetMapping
	public ResponseEntity<List<Restaurant>> getRestaurants(
			@RequestParam(name = "cuisine", required = false) String cuisine) {
		if (cuisine == null)
			return ResponseEntity.ok(restaurantService.getAllRestaurants());
		return ResponseEntity.ok(restaurantService.getRestaurantsByCuisine(cuisine));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Restaurant> getRestaurant(@PathVariable Integer id) {
		return ResponseEntity.ok(restaurantService.getRestaurantById(id));
	}
	
	@PostMapping
	public ResponseEntity<Void> addRestaurant(@RequestBody Restaurant restaurant) {
		restaurantService.createRestaurant(restaurant);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateRestaurant(@PathVariable Integer id, @RequestBody Map<String, Object> changes) {
		restaurantService.updateRestaurant(id, changes);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRestaurant(@PathVariable Integer id) {
		restaurantService.deleteRestaurant(id);
		return ResponseEntity.noContent().build();
	}
}
