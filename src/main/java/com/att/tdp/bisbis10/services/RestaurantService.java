package com.att.tdp.bisbis10.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.att.tdp.bisbis10.entities.*;
import com.att.tdp.bisbis10.repositories.RestaurantRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepo restaurantRepo;

	public Restaurant createRestaurant(Restaurant restaurant) {
		return restaurantRepo.save(restaurant);
	}

	public List<Restaurant> getAllRestaurants() {
		return restaurantRepo.findAll();
	}

	public Restaurant getRestaurantById(Integer id) {
		Optional<Restaurant> restaurant = restaurantRepo.findById(id);
		if (restaurant.isPresent())
			return restaurant.get();
		throw new EntityNotFoundException(String.format("Restaurant with ID %d not found", id));
	}

	public List<Restaurant> getRestaurantsByCuisine(String cuisineName) {
		return restaurantRepo.findAllById(restaurantRepo.findIdByCuisine(cuisineName));
	}

	public Restaurant updateRestaurant(Integer id, Map<String, Object> updates) {
		Optional<Restaurant> resOpt = restaurantRepo.findById(id);
		if (resOpt.isPresent()) {
			Restaurant restaurant = resOpt.get();
			updates.forEach((k, v) -> {
				switch (k) {
				case "name":
					restaurant.setName((String) v);
					break;
				case "isKosher":
					restaurant.setIsKosher((boolean) v);
					break;
				case "cuisines":
					restaurant.setCuisines(new HashSet<String>((Collection<? extends String>) v));
					break;
				default:
					throw new IllegalArgumentException(String.format("Illegal field name: %s", k));
				}
			});

			return restaurantRepo.save(restaurant);
		} else {
			throw new EntityNotFoundException(String.format("Restaurant with ID %d not found", id));
		}
	}

	public void deleteRestaurant(Integer id) {
		restaurantRepo.deleteById(id);
	}
}
