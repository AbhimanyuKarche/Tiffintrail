package com.cdac.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entity.Cart;
import com.cdac.entity.CustomerProfile;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByCustomer(CustomerProfile customer);
}