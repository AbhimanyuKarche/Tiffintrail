package com.cdac.dto;
import java.time.LocalDate;

import com.cdac.enums.PlanType;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class SubscriptionPlanRequestDto {

    @NotNull
    private PlanType planType;

    @PastOrPresent
    @NotNull
    private LocalDate startDate;

    @Future
    @NotNull
    private LocalDate endDate;

    private boolean isActive;

    private String description;

    @NotNull
    private Long customerId;

    @NotNull
    private Long sellerId;
}
