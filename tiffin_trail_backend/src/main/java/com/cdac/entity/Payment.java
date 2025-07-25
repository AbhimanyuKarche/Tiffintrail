package com.cdac.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

public class Payment {
	private int paymentId;
	private double payment;
	private String paymentType;
	private int orderId;
	private String status;
	private LocalDateTime paymentTime;
	@OneToMany(mappedBy = "payment",cascade=CascadeType.ALL,orphanRemoval = true)
	private List<Order> orders=new ArrayList<>();
	
}
