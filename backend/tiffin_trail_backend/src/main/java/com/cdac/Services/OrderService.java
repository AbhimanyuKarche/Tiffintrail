package com.cdac.Services;

import java.util.List;
import java.util.Optional;

import com.cdac.dto.OrderRequestDto;
import com.cdac.dto.OrderResponseDto;
import com.cdac.entity.CustomerProfile;
import com.cdac.entity.Order;

public interface OrderService {
    OrderResponseDto placeOrder(OrderRequestDto order);
    List<OrderResponseDto> getOrdersByCustomer(CustomerProfile customer);
    Optional<OrderResponseDto> getOrderById(Long id);
}
