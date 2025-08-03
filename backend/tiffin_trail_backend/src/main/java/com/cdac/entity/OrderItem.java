package com.cdac.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many order items belong to one order
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // Each item corresponds to one tiffin
    @ManyToOne
    @JoinColumn(name = "tiffin_id")
    private Tiffin tiffin;

    private int quantity;

    private double price; // price at time of order
	
}
