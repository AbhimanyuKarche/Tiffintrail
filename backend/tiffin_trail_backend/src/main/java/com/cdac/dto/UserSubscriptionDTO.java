package com.cdac.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSubscriptionDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private boolean paid;
    private SubscriptionPlanDTO plan;
}