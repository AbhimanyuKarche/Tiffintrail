package com.cdac.dto;

import com.cdac.entity.CartItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemResponseDTO {
    private Long id;
    private String tiffinTitle;
    private int quantity;
    private double pricePerUnit;
    private double totalPrice;
    private String imageUrl;
    private Long tiffinId;

    // Constructor
    public CartItemResponseDTO(Long id, String tiffinTitle, int quantity, double pricePerUnit, double totalPrice, String imageUrl, Long tiffinId) {
        this.id = id;
        this.tiffinTitle = tiffinTitle;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.totalPrice = totalPrice;
        this.imageUrl = imageUrl;
        this.tiffinId = tiffinId;
    }

    // Static factory method for mapping
    public static CartItemResponseDTO fromEntity(CartItem item) {
        return new CartItemResponseDTO(
                item.getId(),
                item.getTiffin().getName(),
                item.getQuantity(),
                item.getTiffin().getPrice(),
                item.getQuantity() * item.getTiffin().getPrice(),
                item.getTiffin().getImageUrl(),
                item.getTiffin().getId()
        );
    }

    // Getters
    public Long getId() { return id; }
    public String getTiffinTitle() { return tiffinTitle; }
    public int getQuantity() { return quantity; }
    public double getPricePerUnit() { return pricePerUnit; }
    public double getTotalPrice() { return totalPrice; }
    public String getImageUrl() { return imageUrl; }
    public Long getTiffinId() { return tiffinId; }
}
