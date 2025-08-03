package com.cdac.dto;

import java.time.LocalDate;

import com.cdac.enums.PlanType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionPlanResponseDto {
    private Long subscriptionId;

    private PlanType planType;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean isActive;

    private String description;

    private Long customerId;

    private Long sellerId;
}
