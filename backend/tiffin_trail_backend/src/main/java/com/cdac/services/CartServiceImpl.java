package com.cdac.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.Repositories.CartItemRepository;
import com.cdac.Repositories.CartRepository;
import com.cdac.entity.Cart;
import com.cdac.entity.CartItem;
import com.cdac.entity.CustomerProfile;
import com.cdac.entity.Tiffin;

import lombok.AllArgsConstructor;


@Transactional
@AllArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepo;
    private final CartItemRepository cartItemRepo;

  

    @Override
    public Cart getCartByCustomer(CustomerProfile customer) {
        return cartRepo.findByCustomer(customer).orElseGet(() -> {
            Cart cart = new Cart();
            cart.setCustomer(customer);
            return cartRepo.save(cart);
        });
    }



    @Override
    public Cart addToCart(CustomerProfile customer, Tiffin tiffin, int quantity) {
        Cart cart = getCartByCustomer(customer);

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getTiffin().getId().equals(tiffin.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            cartItemRepo.save(item);
        } else {
            CartItem item = new CartItem();
            item.setCart(cart);
            item.setTiffin(tiffin);
            item.setQuantity(quantity);
            item.setPrice(tiffin.getPrice());

            cart.getItems().add(cartItemRepo.save(item));
        }

        return cartRepo.save(cart);
    }


    @Override
    public Cart updateCartItem(CustomerProfile customer, Long itemId, int quantity) {
        CartItem item = cartItemRepo.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        if (!item.getCart().getCustomer().getId().equals(customer.getId())) {
            throw new RuntimeException("Unauthorized access to cart item");
        }

        item.setQuantity(quantity);
        cartItemRepo.save(item);

        return item.getCart();
    }


    @Override
    public void removeCartItem(CustomerProfile customer, Long itemId) {
        CartItem item = cartItemRepo.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        if (!item.getCart().getCustomer().getId().equals(customer.getId())) {
            throw new RuntimeException("Unauthorized access to cart item");
        }

        Cart cart = item.getCart();
        cart.getItems().remove(item);
        cartItemRepo.delete(item);
        cartRepo.save(cart);
    }

    @Override
    public void clearCart(CustomerProfile customer) {
        Cart cart = getCartByCustomer(customer);
        cartItemRepo.deleteAll(cart.getItems());
        cart.getItems().clear();
        cartRepo.save(cart);
    }

    @Override
    public List<CartItem> getCartItems(CustomerProfile customer) {
        return getCartByCustomer(customer).getItems();
    }
}
 