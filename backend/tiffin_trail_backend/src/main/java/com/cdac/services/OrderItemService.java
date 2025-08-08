package com.cdac.services;

import java.util.List;

import com.cdac.dto.OrderItemRequestDto;
import com.cdac.dto.OrderItemResponseDto;
import com.cdac.entity.Order;

public interface OrderItemService {
    List<OrderItemResponseDto> saveOrderItems(List<OrderItemRequestDto> items);
    List<OrderItemResponseDto> getItemsByOrder(Order order);
}