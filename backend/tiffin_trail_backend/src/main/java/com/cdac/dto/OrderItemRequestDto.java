package com.cdac.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequestDto {
    private Long tiffinId;
    private int quantity;
}
