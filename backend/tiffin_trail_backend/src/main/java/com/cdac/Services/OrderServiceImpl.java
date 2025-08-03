package com.cdac.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.Repositories.OrderRepository;
import com.cdac.dto.OrderRequestDto;
import com.cdac.dto.OrderResponseDto;
import com.cdac.entity.CustomerProfile;
import com.cdac.entity.Order;
import com.cdac.enums.OrderStatus;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;
    private final ModelMapper modelMapper;

    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {
        // Convert DTO to entity
        Order order = modelMapper.map(orderRequestDto, Order.class);

        // Set order reference in each item
        if (order.getItems() != null) {
            order.getItems().forEach(item -> item.setOrder(order));
        }

        // Set order date and status
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PLACED);

        // Save order
        Order savedOrder = orderRepo.save(order);

        // Convert entity to response DTO
        return modelMapper.map(savedOrder, OrderResponseDto.class);
    }


    @Override
    public List<OrderResponseDto> getOrdersByCustomer(CustomerProfile customer) {
        return orderRepo.findByCustomer(customer).stream()
                .map(order -> modelMapper.map(order, OrderResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderResponseDto> getOrderById(Long id) {
        return orderRepo.findById(id)
                .map(order -> modelMapper.map(order, OrderResponseDto.class));
    }
}
