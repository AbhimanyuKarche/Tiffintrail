package com.cdac.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entity.DeliveryAddress;

public interface DeliveryAddressDao extends JpaRepository<DeliveryAddress, Long> {

}
