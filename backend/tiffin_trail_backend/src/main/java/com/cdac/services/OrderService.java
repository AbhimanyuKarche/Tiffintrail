package com.cdac.services;

import java.util.List;
import java.util.Optional;

import com.cdac.entity.CustomerProfile;
import com.cdac.entity.Order;

public interface OrderService {
	Order placeOrder(CustomerProfile customer) throws Exception ;
    List<Order> getOrdersByCustomer(CustomerProfile customer);
    Optional<Order> getOrderById(Long id);
    Optional<Order> getOrderByRazorpayOrderId(String razorpayOrderId);
    Order save(Order order);
   }
