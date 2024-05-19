package com.att.tdp.bisbis10.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurant")
public class Restaurant implements Serializable {

	private static final long serialVersionUID = 103829418446715062L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "is_kosher")
	private Boolean isKosher;

	@JsonIgnore
	@OneToMany(mappedBy = "restaurant")
	private Set<Rating> ratings;
	
	@JsonIgnore
	@Column
	private Integer dishCount;
	
//
//	@Column(name = "avg_rating")
//	private Float avgRating;
//	
//	@Column(name = "rating_changed")
//	private boolean ratingChanged; // Was there a change in the rating since last average calculation?

	@Column
	private Set<String> cuisines;

	@JsonIgnore
	@OneToMany(mappedBy = "restaurant")
	private List<Order> orders;

	@OneToMany(mappedBy = "restaurant")
	private List<Dish> dishes;

	public Restaurant(String name, Boolean kosher) {
		this.name = name;
		this.isKosher = kosher;
		this.dishCount = 0;
//		ratings = new Set<Rating>();
//		avgRating = 0.0F;
//		ratingChanged = false;
//		this.cuisines = cuisines;
	}

	public Restaurant() {
		this("", false);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean isKosher() {
		return isKosher;
	}

	public void setIsKosher(Boolean kosher) {
		this.isKosher = kosher;
	}

	@JsonProperty(value = "averageRating")
	public float getAvgRating() {
		double sum = 0;
		for (Rating r : ratings)
			sum += r.getStars();
		return (float) (sum / ratings.size());
	}
//
//	public void setRatingChanged() {
//		ratingChanged = true;
//	}

	public Set<String> getCuisines() {
		return cuisines;
	}

	public void setCuisines(Set<String> cuisines) {
		this.cuisines = cuisines;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Set<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(Set<Rating> ratings) {
		this.ratings = ratings;
	}
	
	public Integer nextDish() {
		return ++dishCount;
	}

//	public boolean isRatingChanged() {
//		return ratingChanged;
//	}
//
//	public void setRatingChanged(boolean ratingChanged) {
//		this.ratingChanged = ratingChanged;
//	}
//
//	public void setAvgRating(float avgRating) {
//		this.avgRating = avgRating;
//	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurant other = (Restaurant) obj;
		return id == other.id;
	}

}
