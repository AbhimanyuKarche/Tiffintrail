package com.cdac.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.Repositories.CustomerRepository;
import com.cdac.Repositories.TiffinRepository;
import com.cdac.Repositories.UserRepository;
import com.cdac.dto.AddToCartRequest;
import com.cdac.dto.CartItemResponseDTO;
import com.cdac.entity.Cart;
import com.cdac.entity.CartItem;
import com.cdac.entity.CustomerProfile;
import com.cdac.entity.Tiffin;
import com.cdac.entity.User;
import com.cdac.services.CartService;
import com.cdac.services.CustomerProfileService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CustomerRepository customerRepo;
    private final TiffinRepository tiffinRepo;
    private final UserRepository userRepo;
    private final CustomerProfileService customerService;


    private String getCurrentUserEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) auth.getPrincipal()).getUsername();
        }
        return null;
    }

    private CustomerProfile getCurrentCustomer() {
        String email = getCurrentUserEmail();
        User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return customerRepo.findByUser(user).orElseThrow(() -> new RuntimeException("Customer profile not found"));
    }

    //  Add to cart
    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestBody AddToCartRequest request) {
        CustomerProfile customer = getCurrentCustomer();
        Tiffin tiffin = tiffinRepo.findById(request.getTiffinId())
                .orElseThrow(() -> new RuntimeException("Tiffin not found"));

        Cart cart = cartService.addToCart(customer, tiffin, request.getQuantity());
        return ResponseEntity.ok(cart);
    }

    //  View cart items
    @GetMapping("/items")
    public ResponseEntity<List<CartItemResponseDTO>> getCartItems(Authentication auth) {
        CustomerProfile customer = customerService.getCustomerFromAuth(auth);
        List<CartItem> items = cartService.getCartItems(customer);

        // Convert to DTO
        List<CartItemResponseDTO> dtos = items.stream().map(CartItemResponseDTO::fromEntity).toList();

        return ResponseEntity.ok(dtos);
    }


    //  Update item quantity
    @PutMapping("/update")
    public ResponseEntity<Cart> updateCartItem(
            @RequestParam Long itemId,
            @RequestParam int quantity
    ) {
        CustomerProfile customer = getCurrentCustomer();
        return ResponseEntity.ok(cartService.updateCartItem(customer, itemId, quantity));
    }

    //  Remove item
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeItem(@RequestParam Long itemId) {
        CustomerProfile customer = getCurrentCustomer();
        cartService.removeCartItem(customer, itemId);
        return ResponseEntity.ok("Item removed");
    }

    //  Clear cart
    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart() {
        CustomerProfile customer = getCurrentCustomer();
        cartService.clearCart(customer);
        return ResponseEntity.ok("Cart cleared");
    }
}
