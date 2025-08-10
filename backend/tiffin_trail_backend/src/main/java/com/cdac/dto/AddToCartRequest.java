package com.cdac.dto;

public class AddToCartRequest {
    private Long tiffinId;
    private int quantity;

    // Getters and Setters
    public Long getTiffinId() {
        return tiffinId;
    }

    public void setTiffinId(Long tiffinId) {
        this.tiffinId = tiffinId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}