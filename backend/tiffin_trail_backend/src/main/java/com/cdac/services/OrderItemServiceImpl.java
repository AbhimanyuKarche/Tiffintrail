package com.cdac.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.Repositories.OrderItemRepository;
import com.cdac.Repositories.TiffinRepository;
import com.cdac.dto.OrderItemRequestDto;
import com.cdac.dto.OrderItemResponseDto;
import com.cdac.entity.Order;
import com.cdac.entity.OrderItem;
import com.cdac.entity.Tiffin;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository itemRepo;
    private final TiffinRepository tiffinRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<OrderItemResponseDto> saveOrderItems(List<OrderItemRequestDto> items) {
        List<OrderItem> orderItems = items.stream().map(dto -> {
            OrderItem item = new OrderItem();
            Tiffin tiffin = tiffinRepo.findById(dto.getTiffinId())
                    .orElseThrow(() -> new RuntimeException("Tiffin not found with ID: " + dto.getTiffinId()));
            item.setTiffin(tiffin);
            item.setQuantity(dto.getQuantity());
            item.setPrice(tiffin.getPrice() * dto.getQuantity());
            return item;
        }).collect(Collectors.toList());

        List<OrderItem> savedItems = itemRepo.saveAll(orderItems);

        return savedItems.stream()
                .map(item -> modelMapper.map(item, OrderItemResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderItemResponseDto> getItemsByOrder(Order order) {
        return itemRepo.findByOrder(order).stream()
                .map(item -> modelMapper.map(item, OrderItemResponseDto.class))
                .collect(Collectors.toList());
    }
}
