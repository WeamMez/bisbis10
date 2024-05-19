package com.att.tdp.bisbis10.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.att.tdp.bisbis10.entities.Dish;
import com.att.tdp.bisbis10.entities.DishCompositeKey;

public interface DishRepo extends JpaRepository<Dish, DishCompositeKey> {
	
	@Query(value = "SELECT id, name, description, price FROM dish WHERE restaurant_id = ?1", nativeQuery = true)
	public List<Dish> findByRestaurant(Integer restaurantId);
}
