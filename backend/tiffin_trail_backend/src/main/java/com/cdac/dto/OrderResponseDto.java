package com.cdac.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private Long customerId;
    private String customerName;
    private List<OrderItemResponseDto> items;
    private Double totalAmount;
    private LocalDateTime orderDate;
    private String status;
}
