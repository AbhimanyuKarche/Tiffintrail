package com.cdac.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequestDto {
    private Long customerId; // Only ID is enough for request

    private List<CartItemRequestDto> items;
}
