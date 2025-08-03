package com.cdac.Services;

import java.util.List;

import com.cdac.dto.CartItemResponseDto;
import com.cdac.dto.CartResponseDto;
import com.cdac.entity.CustomerProfile;
import com.cdac.entity.Tiffin;

public interface CartService {
	CartResponseDto getCartByCustomer(CustomerProfile customer);
    CartResponseDto addToCart(CustomerProfile customer, Tiffin tiffin, int quantity);
    CartResponseDto updateCartItem(CustomerProfile customer, Long itemId, int quantity);
    void removeCartItem(CustomerProfile customer, Long itemId);
    void clearCart(CustomerProfile customer);
    List<CartItemResponseDto> getCartItems(CustomerProfile customer);
}
