package com.cdac.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cdac.Services.*;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/orders")
@CrossOrigin
@AllArgsConstructor

public class OrderController {
	private OrderService orderServices;
	
	@GetMapping("/getAllOrderDetails")
	public ResponseEntity<?>getAllOrderDetails(){
		
		 return ResponseEntity.ok(orderServices.getAllOrderDetails());
	
	}
	
}
