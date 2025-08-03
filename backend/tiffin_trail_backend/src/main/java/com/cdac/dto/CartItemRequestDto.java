package com.cdac.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemRequestDto {
    private Long tiffinId;
    private int quantity;
}
