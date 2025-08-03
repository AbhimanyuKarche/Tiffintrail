package com.cdac.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.Repositories.CartItemRepository;
import com.cdac.Repositories.CartRepository;
import com.cdac.dto.CartItemResponseDto;
import com.cdac.dto.CartResponseDto;
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
    private final ModelMapper modelMapper;

    @Override
    public CartResponseDto getCartByCustomer(CustomerProfile customer) {
        Cart cart = cartRepo.findByCustomer(customer)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setCustomer(customer);
                    return cartRepo.save(newCart);
                });
        return convertToCartResponseDto(cart);
    }

    @Override
    public CartResponseDto addToCart(CustomerProfile customer, Tiffin tiffin, int quantity) {
        Cart cart = getOrCreateCart(customer);

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setTiffin(tiffin);
        item.setQuantity(quantity);
        item.setPrice(tiffin.getPrice());

        cart.getItems().add(item);
        cartItemRepo.save(item);
        cartRepo.save(cart);

        return convertToCartResponseDto(cart);
    }

    @Override
    public CartResponseDto updateCartItem(CustomerProfile customer, Long itemId, int quantity) {
        Cart cart = getOrCreateCart(customer);

        for (CartItem item : cart.getItems()) {
            if (item.getId().equals(itemId)) {
                item.setQuantity(quantity);
                cartItemRepo.save(item);
                break;
            }
        }

        return convertToCartResponseDto(cart);
    }

    @Override
    public void removeCartItem(CustomerProfile customer, Long itemId) {
        Cart cart = getOrCreateCart(customer);
        cart.getItems().removeIf(item -> item.getId().equals(itemId));
        cartItemRepo.deleteById(itemId);
        cartRepo.save(cart);
    }

    @Override
    public void clearCart(CustomerProfile customer) {
        Cart cart = getOrCreateCart(customer);
        cartItemRepo.deleteAll(cart.getItems());
        cart.getItems().clear();
        cartRepo.save(cart);
    }

    @Override
    public List<CartItemResponseDto> getCartItems(CustomerProfile customer) {
        Cart cart = getOrCreateCart(customer);
        return cart.getItems()
                .stream()
                .map(item -> modelMapper.map(item, CartItemResponseDto.class))
                .collect(Collectors.toList());
    }

    // Utility Methods

    private Cart getOrCreateCart(CustomerProfile customer) {
        Optional<Cart> cartOptional = cartRepo.findByCustomer(customer);
        return cartOptional.orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setCustomer(customer);
            return cartRepo.save(newCart);
        });
    }

    private CartResponseDto convertToCartResponseDto(Cart cart) {
        CartResponseDto cartDto = modelMapper.map(cart, CartResponseDto.class);
        List<CartItemResponseDto> itemDtos = cart.getItems()
                .stream()
                .map(item -> modelMapper.map(item, CartItemResponseDto.class))
                .collect(Collectors.toList());
        cartDto.setItems(itemDtos);
        return cartDto;
    }
}
