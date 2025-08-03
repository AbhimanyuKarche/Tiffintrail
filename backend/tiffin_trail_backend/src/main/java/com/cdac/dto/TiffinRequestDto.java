package com.cdac.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TiffinRequestDto {
    private String name;
    private String description;
    private Double price;
    private boolean isVeg;
    private String imageUrl;
    private boolean available;

    private Long sellerId;         // ID of seller to link with seller entity
    private Long subscriptionId;   // ID of subscription plan to link with subscription
}
