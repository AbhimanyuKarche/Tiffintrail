package com.cdac.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @OneToOne
	    @JoinColumn(name = "customer_id", nullable = false)
	    private CustomerProfile customer;

	    private LocalDateTime createdAt = LocalDateTime.now();

	    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<CartItem> items = new ArrayList<>();

	    // Constructors
	    public Cart() {}

	    public Cart(CustomerProfile customer) {
	        this.customer = customer;
	    }
	
}
