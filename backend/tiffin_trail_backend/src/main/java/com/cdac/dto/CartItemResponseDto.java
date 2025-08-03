package com.cdac.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemResponseDto {
    private Long id;
    private Long tiffinId;
    private String tiffinName;
    private int quantity;
    private double price;        // Unit price
    private double totalPrice;   // quantity * price
}
