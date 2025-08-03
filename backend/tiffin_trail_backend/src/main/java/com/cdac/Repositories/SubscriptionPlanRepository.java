package com.cdac.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entity.CustomerProfile;
import com.cdac.entity.SellerProfile;
import com.cdac.entity.SubscriptionPlan;

public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Long> {
	
  
	List<SubscriptionPlan> findByCustomerProfileId(Long customerId);

	List<SubscriptionPlan> findBySellerProfileId(Long sellerId);

	
}
