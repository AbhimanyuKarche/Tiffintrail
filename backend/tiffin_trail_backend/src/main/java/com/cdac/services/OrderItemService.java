package com.cdac.services;

import java.util.List;

import com.cdac.entity.Order;
import com.cdac.entity.OrderItem;

public interface OrderItemService {
	 List<OrderItem> saveOrderItems(List<OrderItem> items);
	    List<OrderItem> getItemsByOrder(Order order);
    }