package com.cdac.entity;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_profiles")

public class CustomerProfile {
	    @Id
	    private Long id;  // This will be same as the User ID
	    @OneToOne
	    @MapsId
	    @JoinColumn(name = "user_id")
	    private User user;

	    private String address;

	    private String city;

	    private String pincode;

	    private String phone;

	    
	    @OneToMany(mappedBy = "customerProfile", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<SubscriptionPlan> subscriptionPlans = new ArrayList<>();

	    // (Use Lombok @Data if you prefer)
	
}
