package com.cdac.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cdac.Repositories.CustomerRepository;
import com.cdac.Repositories.TiffinRepository;
import com.cdac.Services.CartService;
import com.cdac.dto.CartItemResponseDto;
import com.cdac.dto.CartResponseDto;
import com.cdac.entity.CustomerProfile;
import com.cdac.entity.Tiffin;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CustomerRepository customerRepo;
    private final TiffinRepository tiffinRepo;

    // Add to cart
    @PostMapping("/add")
    public ResponseEntity<CartResponseDto> addToCart(
            @RequestParam Long customerId,
            @RequestParam Long tiffinId,
            @RequestParam int quantity
    ) {
        CustomerProfile customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Tiffin tiffin = tiffinRepo.findById(tiffinId)
                .orElseThrow(() -> new RuntimeException("Tiffin not found"));

        CartResponseDto cart = cartService.addToCart(customer, tiffin, quantity);
        return ResponseEntity.ok(cart);
    }

    // View cart items
    @GetMapping("/{customerId}")
    public ResponseEntity<List<CartItemResponseDto>> getCartItems(@PathVariable Long customerId) {
        CustomerProfile customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return ResponseEntity.ok(cartService.getCartItems(customer));
    }

    // Update item quantity
    @PutMapping("/update")
    public ResponseEntity<CartResponseDto> updateCartItem(
            @RequestParam Long customerId,
            @RequestParam Long itemId,
            @RequestParam int quantity
    ) {
        CustomerProfile customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return ResponseEntity.ok(cartService.updateCartItem(customer, itemId, quantity));
    }

    // Remove item
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeItem(
            @RequestParam Long customerId,
            @RequestParam Long itemId
    ) {
        CustomerProfile customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        cartService.removeCartItem(customer, itemId);
        return ResponseEntity.ok("Item removed");
    }

    // Clear cart
    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart(@RequestParam Long customerId) {
        CustomerProfile customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        cartService.clearCart(customer);
        return ResponseEntity.ok("Cart cleared");
    }
}
