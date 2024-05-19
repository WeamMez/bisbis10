package com.att.tdp.bisbis10.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class DishCompositeKey implements Serializable {
	
	private static final long serialVersionUID = -623706873136708443L;
	
	private Integer restaurant; // FK
	
	private Integer id;
	
	public DishCompositeKey(Integer restaurantId, Integer id) {
		this.restaurant = restaurantId;
		this.id = id;
	}
	
	public DishCompositeKey() {}

	public Integer getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Integer restaurantId) {
		this.restaurant = restaurantId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, restaurant);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DishCompositeKey other = (DishCompositeKey) obj;
		return id == other.id && restaurant == other.restaurant;
	}
	
	
}
