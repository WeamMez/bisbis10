package com.att.tdp.bisbis10.entities;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "dish")
@IdClass(DishCompositeKey.class)
public class Dish implements Serializable {
//	@EmbeddedId
//	private DishCompositeKey key;
	
	private static final long serialVersionUID = -1954719967532074995L;

	@Id
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "restaurant_id")//, insertable = false, updatable = false)
	private Restaurant restaurant;
	
	@Id
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	private Double price; // in AGOROT (0.01 NIS)
	
	public static final char CURRENCY = '₪';
	public static final double FACTOR = 100;
	
	public Dish(Restaurant restaurant, String name, String description, Double price) {
		this.restaurant = restaurant;
		this.name = name;
		this.description = description;
		this.price = price;
		this.id = restaurant.nextDish();
	}
	
	public Dish() {
		id = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

//	public DishCompositeKey getKey() {
//		return key;
//	}
//	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
		this.id = restaurant.nextDish();
	}
	
//	public void setRestaurantId(Integer id) {
//		key.setRestaurantId(id);
//	}

	@Override
	public int hashCode() {
		return Objects.hash(restaurant, id, name); //, restaurant);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dish other = (Dish) obj;
		return Objects.equals(restaurant, other.restaurant) && Objects.equals(id, other.id) && Objects.equals(name, other.name);
				//&& Objects.equals(restaurant, other.restaurant);
	}
	
	
}
