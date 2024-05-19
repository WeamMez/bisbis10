package com.att.tdp.bisbis10.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.att.tdp.bisbis10.entities.Order;
import com.att.tdp.bisbis10.repositories.OrderRepo;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepo orderRepo;
	
	public Order createOrder(Order order) {
		return orderRepo.save(order);
	}
}
