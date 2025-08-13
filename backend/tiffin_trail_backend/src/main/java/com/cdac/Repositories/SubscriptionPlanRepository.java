package com.cdac.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entity.SubscriptionPlan;

public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Long> {
	
	 List<SubscriptionPlan> findBySellerId(Long sellerId);
	
}
