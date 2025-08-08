package com.cdac.services;

import java.util.List;

import com.cdac.dto.SubscriptionPlanRequestDto;
import com.cdac.dto.SubscriptionPlanResponseDto;

public interface SubscriptionPlanService {
	SubscriptionPlanResponseDto addSubscriptionPlan(SubscriptionPlanRequestDto dto);

    List<SubscriptionPlanResponseDto> getAllPlans();

    SubscriptionPlanResponseDto getPlanById(Long id);

    SubscriptionPlanResponseDto updatePlan(Long id, SubscriptionPlanRequestDto dto);

    void deletePlan(Long id);
    
    List<SubscriptionPlanResponseDto> getPlansByUser(Long userId);
    List<SubscriptionPlanResponseDto> getPlansByVendor(Long vendorId);
   
}
