package com.cdac.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cdac.entity.Cart;
import com.cdac.entity.CustomerProfile;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByCustomer(CustomerProfile customer);
    @Query("SELECT c FROM Cart c LEFT JOIN FETCH c.items WHERE c.customer = :customer")
    Optional<Cart> findByCustomerWithItems(@Param("customer") CustomerProfile customer);


}