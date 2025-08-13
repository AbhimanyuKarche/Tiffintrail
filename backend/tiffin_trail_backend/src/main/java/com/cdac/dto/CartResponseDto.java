package com.cdac.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartResponseDto {
    private Long id;
     private LocalDateTime createdAt;
     private CustomerProfileResponseDTO customer;
    private List<CartItemResponseDTO> items;
}
