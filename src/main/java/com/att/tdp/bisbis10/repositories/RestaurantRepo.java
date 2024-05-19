package com.att.tdp.bisbis10.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.att.tdp.bisbis10.entities.Restaurant;

public interface RestaurantRepo extends JpaRepository<Restaurant, Integer> {
	
	@Query(value = "SELECT * FROM restaurant WHERE ?1 =  ANY (cuisines)", nativeQuery = true)
	public List<Integer> findIdByCuisine(String cuisine);
}
