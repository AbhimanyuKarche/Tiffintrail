package com.cdac.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponseDto {
    private Long id;
    private Long tiffinId;
    private String tiffinName;
    private int quantity;
    private double price;
}
