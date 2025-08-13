package com.cdac.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubscriptionPlanDTO {
    private Long id;
    private String name;
    private double price;
}
