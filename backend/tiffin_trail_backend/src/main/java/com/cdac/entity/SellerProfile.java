package com.cdac.entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cdac.enums.PlanType;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seller_profiles")
public class SellerProfile {
	  @Id
	    private Long id;  // This will be same as the User ID


	    @OneToOne
	    @MapsId
	    @JoinColumn(name = "user_id")
	    private User user;

	    private String businessName;

	    private String description;

	    private String address;

	    private String city;

	    private String pincode;

	    private String phone;
	    
	    @OneToMany(mappedBy = "sellerProfile", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<SubscriptionPlan> subscriptionPlans = new ArrayList<>();

	    
}
