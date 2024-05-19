package com.att.tdp.bisbis10.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.att.tdp.bisbis10.entities.Order;

public interface OrderRepo extends JpaRepository<Order, UUID> {
//	@Query(value = "SELECT * FROM rest_order WHERE restaurant_id = ?", nativeQuery = true)
//	public List<Order> findByRestaurant(Integer restaurant_id);
}
