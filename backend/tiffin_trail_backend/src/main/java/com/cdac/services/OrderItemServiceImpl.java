package com.cdac.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.Repositories.OrderItemRepository;
import com.cdac.entity.Order;
import com.cdac.entity.OrderItem;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository itemRepo;

   

    @Override
    public List<OrderItem> saveOrderItems(List<OrderItem> items) {
        return itemRepo.saveAll(items);
    }

    @Override
    public List<OrderItem> getItemsByOrder(Order order) {
        return itemRepo.findByOrder(order);
    }
}

