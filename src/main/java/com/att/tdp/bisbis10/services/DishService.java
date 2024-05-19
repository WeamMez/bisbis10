package com.att.tdp.bisbis10.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.att.tdp.bisbis10.entities.Dish;
import com.att.tdp.bisbis10.entities.DishCompositeKey;
import com.att.tdp.bisbis10.repositories.DishRepo;
import com.att.tdp.bisbis10.repositories.RestaurantRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DishService {

	@Autowired
	private DishRepo dishRepo;
	@Autowired
	private RestaurantRepo restaurantRepo;

	public Dish createDish(Integer restaurantId, Dish dish) {
		dish.setRestaurant(restaurantRepo.findById(restaurantId).orElseThrow());
		return dishRepo.save(dish);
	}

	public List<Dish> getDishesByRestaurant(Integer restaurantId) {
		return dishRepo.findByRestaurant(restaurantId);
	}

	public Dish updateDish(Integer restaurantId, Integer dishId, Map<String, Object> updates) {
		Optional<Dish> dishOpt = dishRepo.findById(new DishCompositeKey(restaurantId, dishId));
		if (dishOpt.isPresent()) {
			Dish dish = dishOpt.get();
			updates.forEach((k, v) -> {
				switch (k) {
				case "id": // We allow this although it's unlikely.
					dish.setId((Integer) v);
					break;
				case "name":
					dish.setName((String) v);
					break;
				case "description":
					dish.setDescription((String) v);
					break;
				case "price":
					dish.setPrice((Double) v);
					break;
				default:
					// We cannot change the restaurant_id as it is illogical.
					throw new IllegalArgumentException(String.format("Illegal field name: %s", k));
				}
			});

			return dishRepo.save(dish);
		} else if (restaurantRepo.findById(restaurantId).isEmpty()) {
			throw new EntityNotFoundException(String.format("Restaurant with ID %d not found", restaurantId));
		} else {
			throw new EntityNotFoundException(
					String.format("Dish ID %d in Restaurant with ID %d not found", dishId, restaurantId));
		}
	}
	
	public void deleteDish(Integer restaurantId, Integer dishId) {
		dishRepo.deleteById(new DishCompositeKey(restaurantId, dishId));
	}
}
