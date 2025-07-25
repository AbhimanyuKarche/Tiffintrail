package com.cdac.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entity.Order;

public interface OrderDao extends JpaRepository<Order, Long>{

}
