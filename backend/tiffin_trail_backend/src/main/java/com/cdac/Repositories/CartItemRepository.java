package com.cdac.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // Add custom queries if needed in future
}
