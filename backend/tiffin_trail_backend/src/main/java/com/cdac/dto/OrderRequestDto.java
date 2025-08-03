package com.cdac.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

import com.cdac.entity.CustomerProfile;

@Getter
@Setter
public class OrderRequestDto {
    private List<OrderItemRequestDto> items;
    private Double totalAmount;
    private CustomerProfile customer;
}
