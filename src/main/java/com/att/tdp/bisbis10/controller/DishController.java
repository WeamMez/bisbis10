package com.att.tdp.bisbis10.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.att.tdp.bisbis10.entities.Dish;
import com.att.tdp.bisbis10.services.DishService;
import com.att.tdp.bisbis10.services.RestaurantService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/restaurants/{id}/dishes")
public class DishController {
	
	@Autowired
	private DishService dishService;
	
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
	
	@PostMapping
	public ResponseEntity<Void> addDish(@PathVariable Integer id, @RequestBody Dish dish) {
		dishService.createDish(id, dish);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/{dishId}")
	public ResponseEntity<Void> updateDish(@PathVariable Integer id, @PathVariable Integer dishId, @RequestBody Map<String, Object> updates) {
		dishService.updateDish(id, dishId, updates);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{dishId}")
	public ResponseEntity<Void> deleteDish(@PathVariable Integer id, @PathVariable Integer dishId) {
		dishService.deleteDish(id, dishId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<Dish>> getDishesByRestaurant(@PathVariable Integer id) {
		return ResponseEntity.ok(restaurantService.getRestaurantById(id).getDishes());
	}
}
