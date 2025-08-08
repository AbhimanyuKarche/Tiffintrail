package com.cdac.entity;
import com.cdac.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    

	    private Double totalAmount;

	    private LocalDateTime orderDate;

	    @Enumerated(EnumType.STRING)
	    private OrderStatus status = OrderStatus.PLACED;
	    
	    
	 // Many orders can belong to one customer
	    @ManyToOne
	    @JoinColumn(name = "customer_id", nullable = false)
	    private CustomerProfile customer;

	    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	    private List<OrderItem> items;
	

}
