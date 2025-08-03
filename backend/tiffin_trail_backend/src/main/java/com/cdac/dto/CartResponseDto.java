package com.cdac.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartResponseDto {
    private Long id;
    private Long customerId;
    private String customerName; // Optional for display
    private LocalDateTime createdAt;
    private List<CartItemResponseDto> items;
}
