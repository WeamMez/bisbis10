package com.att.tdp.bisbis10.entities;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

@Entity
@Table(name = "rating")
public class Rating implements Serializable {
	private static final long serialVersionUID = 4639232530779822310L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
	private Restaurant restaurant;
	
	@JsonAlias(value = "rating")
	@Column(name = "stars")
	private float stars;

	public Rating(Restaurant restaurant, float stars) {
		this.restaurant = restaurant;
		this.stars = stars;
	}
	
	public Rating() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public float getStars() {
		return stars;
	}

	public void setStars(short stars) {
		this.stars = stars;
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
		Rating other = (Rating) obj;
		return id == other.id && Objects.equals(restaurant, other.restaurant);
	}
	
	
}
