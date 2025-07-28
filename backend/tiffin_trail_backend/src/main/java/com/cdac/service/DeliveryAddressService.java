package com.cdac.service;

import java.util.List;

import com.cdac.entity.DeliveryAddress;

public interface DeliveryAddressService {
	
	DeliveryAddress create(DeliveryAddress address);
	
	List<DeliveryAddress> getAll();
	
	List<DeliveryAddress> getByUserId(Long userId);
	
	DeliveryAddress update (Long addressId, DeliveryAddress updatedAddress);
	
	// DeliveryAddress update (Long userId, DeliveryAddress updatedAddress);
	
	boolean delete (Long addressId);

	//boolean deleteAddress  (Long userId);
}
