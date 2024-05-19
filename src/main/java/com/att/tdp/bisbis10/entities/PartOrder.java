package com.att.tdp.bisbis10.entities;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "part_order")
public class PartOrder implements Serializable {

	private static final long serialVersionUID = -2898219287381283530L;

	public static final short DEFAULT_AMOUNT = 1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@ManyToOne
//	@JoinColumn(name = "restaurant_id", insertable=false, updatable=false)
//	private Restaurant restaurant;

	@ManyToOne
	@JoinColumns({@JoinColumn(name = "dish_id", referencedColumnName = "id"), @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")})
	private Dish dish;
	
	@ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
	private Order restOrder;

	@Column
	private short amount;

	public PartOrder(Order restOrder, Dish dish, short amount) {
		this.restOrder = restOrder;
		this.dish = dish;
		this.amount = amount;
	}

	public PartOrder(Order order, Dish dish) {
		this(order, dish, DEFAULT_AMOUNT);
	}

//	public Restaurant getRestaurant() {
//		return restaurant;
//	}

	public short getAmount() {
		return amount;
	}

	public void setAmount(short amount) {
		this.amount = amount;
	}

	public Dish getDish() {
		return dish;
	}

	public Long getId() {
		return id;
	}
}
