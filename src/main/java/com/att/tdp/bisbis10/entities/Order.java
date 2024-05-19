package com.att.tdp.bisbis10.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "rest_order")
public class Order implements Serializable {

	private static final long serialVersionUID = 2388280570096876610L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@ManyToOne
	@JoinColumn(name = "restaurant_id", referencedColumnName = "id")//, insertable = false, updatable = false)
	private Restaurant restaurant;
	
	@OneToMany(mappedBy = "restOrder")//, cascade = CascadeType.ALL)
	private List<PartOrder> orderItems;

	public Order() {
	}

	public UUID getId() {
		return id;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

//	public List<PartOrder> getOrderItems() {
//		return orderItems;
//	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

//	public void setOrderItems(List<PartOrder> orderItems) {
//		this.orderItems = orderItems;
//	}

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
		Order other = (Order) obj;
		return id.equals(other.id) && Objects.equals(restaurant, other.restaurant);
	}
}
