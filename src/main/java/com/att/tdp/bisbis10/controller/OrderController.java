package com.att.tdp.bisbis10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.att.tdp.bisbis10.entities.Order;
import com.att.tdp.bisbis10.services.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public ResponseEntity<String> addOrder(@RequestBody Order order) {
		Order created = orderService.createOrder(order);
		return ResponseEntity.ok(String.format("{orderId: \"%s\"}", created.getId()));
	}
}
