package com.cdac.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TiffinResponseDTO {
	private Long id;
    private String title;
    private String description;
    private double price;
    private String sellerName;
    private String sellerEmail;
    private String imageUrl;
}
