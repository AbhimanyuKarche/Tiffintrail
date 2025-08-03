package com.cdac.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TiffinResponseDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private boolean isVeg;
    private String imageUrl;
    private boolean available;

    private Long sellerId;
    private String sellerName;  // optional for display

    private Long subscriptionId;
    private String subscriptionType; // optional display field
}
