package com.cdac.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entity.CustomerProfile;
import com.cdac.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer(CustomerProfile customer);
    Optional<Order> findByRazorpayOrderId(String razorpayOrderId);
	}

