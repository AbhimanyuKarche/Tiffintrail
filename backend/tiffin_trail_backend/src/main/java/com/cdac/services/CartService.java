package com.cdac.services;

import java.util.List;

import com.cdac.entity.Cart;
import com.cdac.entity.CartItem;
import com.cdac.entity.CustomerProfile;
import com.cdac.entity.Tiffin;

public interface CartService {
	 Cart getCartByCustomer(CustomerProfile customer);
	    Cart addToCart(CustomerProfile customer, Tiffin tiffin, int quantity);
	    Cart updateCartItem(CustomerProfile customer, Long itemId, int quantity);
	    void removeCartItem(CustomerProfile customer, Long itemId);
	    void clearCart(CustomerProfile customer);
	    List<CartItem> getCartItems(CustomerProfile customer);
	}
