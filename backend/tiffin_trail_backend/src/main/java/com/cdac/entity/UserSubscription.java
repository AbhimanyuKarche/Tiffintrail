package com.cdac.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSubscription {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    // Link to the user who subscribed
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "user_id", nullable = false)
	    private User user;

	    // Link to the subscription plan
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "plan_id", nullable = false)
	    private SubscriptionPlan plan;

	    @Column(nullable = false)
	    private LocalDate startDate;

	    @Column(nullable = false)
	    private LocalDate endDate;

	    // Status: ACTIVE, EXPIRED, CANCELLED
	    @Column(nullable = false)
	    private String status;

	    // Payment details
	    @Column(nullable = false)
	    private String paymentStatus; // PENDING, PAID
	    @Column(nullable = false)
	    private String paymentMode;   // OFFLINE, ONLINE

	    // getters & setters
}
