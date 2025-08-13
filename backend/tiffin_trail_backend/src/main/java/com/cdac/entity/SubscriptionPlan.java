package com.cdac.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@Entity
@Table(name = "subscription_plan")

public class SubscriptionPlan {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;  // e.g., "Weekly Plan", "Monthly Plan"
	    private Double price;
	    private int durationDays;
	    private String description;

	    private boolean active;

	    // Link to seller (User entity)
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "seller_id", nullable = false)
	    private User seller;

	    // Link to the Tiffin entity
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "tiffin_id", nullable = false)
	    private Tiffin tiffin;
}
