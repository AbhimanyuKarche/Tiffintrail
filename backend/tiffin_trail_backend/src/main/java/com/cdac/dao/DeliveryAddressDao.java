package com.cdac.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entity.DeliveryAddress;

public interface DeliveryAddressDao extends JpaRepository<DeliveryAddress, Long> {
	 List<DeliveryAddress> findByUserUserId(Long userId);
}
